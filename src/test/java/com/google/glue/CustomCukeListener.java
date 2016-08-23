package com.google.glue;

import com.frameworkium.core.common.reporting.jira.JiraConfig;
import com.frameworkium.core.common.reporting.jira.zapi.Execution;
import cucumber.runtime.StepDefinitionMatch;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.*;
import gherkin.formatter.model.Step;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.model.*;
import ru.yandex.qatools.allure.config.AllureModelUtils;
import ru.yandex.qatools.allure.events.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomCukeListener implements Formatter, Reporter {

    private Allure lifecycle = Allure.LIFECYCLE;
    public Allure getLifecycle() {
        return lifecycle;
    }

    private Feature featureInUse;
    private Boolean logSteps = true;
    private Boolean logScenarioAsTest = false;
    private Boolean logScenarioAsStep = true;
    private int feaStepBrokenCount = 0;
    private int scnStepBrokenCount = 0;
    private Throwable latestError;
    private Boolean hasBackground = false;

    @Override
    public void syntaxError(String s, String s1, List<String> list, String s2, Integer integer) {

    }

    @Override
    public void uri(String s) {

    }

    @Override
    public void feature(Feature feature) {
        feaStepBrokenCount=0;
        featureInUse = feature;
        updateTCMStatus(getTestCaseId(feature.getTags()), JiraConfig.ZapiStatus.ZAPI_STATUS_WIP, "");
        getLifecycle().fire(new TestSuiteStartedEvent(feature.getId(),feature.getName()).withTitle(feature.getName()));
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
    }

    @Override
    public void examples(Examples examples) {

    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        scnStepBrokenCount=0;
        if(logScenarioAsTest) {

            getLifecycle().fire(new TestCaseStartedEvent(featureInUse.getId(), scenario.getName())
                    .withDescription(new Description().withValue(featureInUse.getDescription()))
                    .withLabels(
                            AllureModelUtils.createFeatureLabel(featureInUse.getName()),
                            AllureModelUtils.createStoryLabel(scenario.getName()),
                            AllureModelUtils.createIssueLabel(getTestCaseId(scenario.getTags()))
                    ));
        } else if (logScenarioAsStep){
            getLifecycle().fire(new StepStartedEvent("[SCN] " + scenario.getName()));
        }

        if(!featureInUse.getDescription().isEmpty())
            getLifecycle().fire(new AddTestCaseDescriptionEvent(featureInUse.getDescription()));

        List<Label> labels = new ArrayList<Label>();
        labels.add(AllureModelUtils.createFeatureLabel(featureInUse.getName()));
        labels.addAll(getStoryLabels(featureInUse, scenario));
        getLifecycle().fire(new AddTestCaseLabelEvent(labels));


        updateTCMStatus(getTestCaseId(scenario.getTags()), JiraConfig.ZapiStatus.ZAPI_STATUS_WIP, "");
    }

    @Override
    public void background(Background background) {
        hasBackground = true;
        getLifecycle().fire(new StepStartedEvent("[BACKGROUND]"));
    }

    @Override
    public void scenario(Scenario scenario) {
        if (hasBackground) {
            //End the background step
            getLifecycle().fire(new StepFinishedEvent());
        }
    }

    @Override
    public void step(Step step) {

    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {

        if(logScenarioAsTest) {
            if (scnStepBrokenCount > 0) {
                getLifecycle().fire(new TestCaseFailureEvent().withThrowable(latestError));
                getLifecycle().fire(new TestCaseFinishedEvent());
            } else {
                getLifecycle().fire(new TestCaseFinishedEvent());
            }

        } else if (logScenarioAsStep){
            if (scnStepBrokenCount > 0) {
                getLifecycle().fire(new StepFailureEvent().withThrowable(latestError));
            }
            getLifecycle().fire(new StepFinishedEvent());
        }

        if(scnStepBrokenCount > 0) {
            updateTCMStatus(getTestCaseId(scenario.getTags()), JiraConfig.ZapiStatus.ZAPI_STATUS_FAIL, latestError.getLocalizedMessage());
        } else {
            updateTCMStatus(getTestCaseId(scenario.getTags()), JiraConfig.ZapiStatus.ZAPI_STATUS_PASS, "");
        }
    }

    @Override
    public void done() {

        getLifecycle().fire(new TestSuiteFinishedEvent(featureInUse.getId()));


        if (feaStepBrokenCount > 0) {
            updateTCMStatus(getTestCaseId(featureInUse.getTags()), JiraConfig.ZapiStatus.ZAPI_STATUS_FAIL, latestError.getLocalizedMessage());
        } else {
            updateTCMStatus(getTestCaseId(featureInUse.getTags()), JiraConfig.ZapiStatus.ZAPI_STATUS_PASS, "");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void eof() {

    }

    @Override
    public void before(Match match, Result result) {

    }

    @Override
    public void result(Result result) {
        if (result.getStatus() != "undefined") {

            if (result.getStatus() != "passed") {
                scnStepBrokenCount++;
                feaStepBrokenCount++;
                latestError = result.getError();
            }

            if (logSteps) {
                if (result.getStatus() != "passed") {
                    getLifecycle().fire(new StepFailureEvent().withThrowable(result.getError()));
                }
                getLifecycle().fire(new StepFinishedEvent());
            }
        }
    }

    @Override
    public void after(Match match, Result result) {

    }

    @Override
    public void match(Match match) {
        if (logSteps) {
            if(match.getLocation() != null) {
                StepDefinitionMatch stepDef = (StepDefinitionMatch) match;
                Step step = (Step) getFieldValueInObject(stepDef, "step");
                getLifecycle().fire(new StepStartedEvent(step.getKeyword() + " " + step.getName()));
            }
        }
    }

    @Override
    public void embedding(String s, byte[] bytes) {

    }

    @Override
    public void write(String s) {

    }









    private <T> Object getFieldValueInObject(T m, String field) {
        try {
            Field fieldInObject = getFieldInObject(m, field);
            fieldInObject.setAccessible(true);
            return fieldInObject.get(m);
        } catch (Exception e) {
            return null;
        }
    }
    private <T> Field getFieldInObject(T m, String field) throws NoSuchFieldException {
        return m.getClass().getDeclaredField(field);
    }

    private void printTags(List<Tag> tags){
        for (Tag tag : tags){
            System.out.println(tag.getName());
        }
    }


    private String getTestCaseId(List<Tag> tags){
        return retrieveTagValue(tags, "TestCaseId");
    }

    private List<Label> getStoryLabels(Feature feature, Scenario scenario){
        List<Label> labels = new ArrayList<Label>();
        List<String> storyAnnotations = new ArrayList<>();

        //Get the story annotations from the feature
        storyAnnotations.addAll(Arrays.asList(retrieveTagValue(feature.getTags(),"Story").split(",")));
        storyAnnotations.addAll(Arrays.asList(retrieveTagValue(feature.getTags(),"Stories").split(",")));

        //Get the story annotations from the scenario
        storyAnnotations.addAll(Arrays.asList(retrieveTagValue(scenario.getTags(),"Story").split(",")));
        storyAnnotations.addAll(Arrays.asList(retrieveTagValue(scenario.getTags(),"Stories").split(",")));

        System.out.println(storyAnnotations.toString());
        for (String storyAnnotation : storyAnnotations){
            if(!storyAnnotation.isEmpty())
                labels.add(AllureModelUtils.createStoryLabel(storyAnnotation.trim()));
        }
        return labels;
    }

    private String retrieveTagValue(List<Tag> tags, String tagName){
        String value = "";
        String tagSearch = "@" + tagName + ":";
        for (Tag tag : tags){
            System.out.println(tag.getName());
            if(tag.getName().startsWith(tagSearch))
                value = tag.getName().replace(tagSearch,"").trim();
        }
        return value;
    }

    private void updateTCMStatus(String testCaseId, int status, String comment){
        if(!testCaseId.isEmpty()) {
            comment = "Updated by CustomCukeListener" + System.lineSeparator() + comment;
            new Execution(testCaseId).update(status, comment, null);
        }
    }


    public class AddTestCaseDescriptionEvent implements TestCaseEvent {
        Description value;

        public AddTestCaseDescriptionEvent(String description) {
            this.value = new Description().withValue(description);
        }

        @Override
        public void process(TestCaseResult context) {
            context.setDescription(value);
        }
    }

    public class AddTestCaseLabelEvent implements TestCaseEvent {
        List<Label> labels;

        public AddTestCaseLabelEvent(List<Label> labels) {
            this.labels = labels;
        }

        @Override
        public void process(TestCaseResult context) {
            context.setLabels(labels);
        }
    }

}
