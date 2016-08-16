package com.google.glue;

import cucumber.runtime.StepDefinitionMatch;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.*;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.config.AllureModelUtils;
import ru.yandex.qatools.allure.events.*;

import java.lang.reflect.Field;
import java.util.List;

public class CustomCukeListener implements Formatter, Reporter {

    private Allure lifecycle = Allure.LIFECYCLE;
    public Allure getLifecycle() {
        return lifecycle;
    }

    private String featureInUse;
    private Boolean logSteps = true;
    private Boolean logScenarioAsTest = false;
    private Boolean logScenarioAsStep = true;
    private int stepBrokenCount;
    private Throwable latestError;

    @Override
    public void syntaxError(String s, String s1, List<String> list, String s2, Integer integer) {

    }

    @Override
    public void uri(String s) {

    }

    @Override
    public void feature(Feature feature) {
        featureInUse = feature.getName();
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
    }

    @Override
    public void examples(Examples examples) {

    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        stepBrokenCount=0;
        if(logScenarioAsTest) {
            getLifecycle().fire(new TestCaseStartedEvent(featureInUse, scenario.getName())
                    .withLabels(
                            AllureModelUtils.createFeatureLabel(featureInUse),
                            AllureModelUtils.createStoryLabel(scenario.getName())
                    ));
        } else if (logScenarioAsStep){
            getLifecycle().fire(new StepStartedEvent("[SCN] " + scenario.getName()));
        }
    }

    @Override
    public void background(Background background) {

    }

    @Override
    public void scenario(Scenario scenario) {

    }

    @Override
    public void step(Step step) {

    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {
        if(logScenarioAsTest) {
            if (stepBrokenCount > 0) {
                getLifecycle().fire(new TestCaseFailureEvent().withThrowable(latestError));
            }
            getLifecycle().fire(new TestCaseFinishedEvent());
        } else if (logScenarioAsStep){
            if (stepBrokenCount > 0) {
                getLifecycle().fire(new StepFailureEvent().withThrowable(latestError));
            }
            getLifecycle().fire(new StepFinishedEvent());
        }
    }

    @Override
    public void done() {

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
                stepBrokenCount++;
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







}
