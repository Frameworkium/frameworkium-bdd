package com.frameworkium.bdd;

import com.frameworkium.core.ui.UITestLifecycle;
import com.frameworkium.core.ui.listeners.CaptureListener;
import com.frameworkium.core.ui.listeners.ScreenshotListener;
import io.cucumber.testng.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({CaptureListener.class, ScreenshotListener.class})
@CucumberOptions(
        features = {"src/test/resources/features/"},
        tags = "not @ignore and not @api",
        plugin = {
                "pretty", // pretty console logging
                "json:target/json/cucumber-results.json", // json results file
                "listener.CukeScreenshotListener",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
        },
        // NB: change these to match your glue packages.
        glue = {"com.google.glue", "org.seleniumhq.glue"})
public class UITestRunner extends AbstractTestNGCucumberTests {
    private static final Logger logger = LogManager.getLogger();

    private TestNGCucumberRunner testNGCucumberRunner;
    private ThreadLocal<String> scenarioName = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        // This is the only UI Test Class so no need for separate @BeforeSuite
        UITestLifecycle.get().beforeSuite();
    }

    @BeforeMethod(alwaysRun = true)
    public void setTestName(Method method, Object[] testData) {
        Pickle pickleEvent = ((PickleWrapper) testData[0]).getPickle();
        String scenarioName = pickleEvent.getName();
        this.scenarioName.set(scenarioName);
        logger.info("START {}", scenarioName);
        UITestLifecycle.get().beforeTestMethod(scenarioName);
    }

    @Test(dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        // the 'featureWrapper' parameter solely exists to display the feature file in a test report
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        UITestLifecycle uiTestLifecycle = UITestLifecycle.get();
            
        if (uiTestLifecycle != null) {
            uiTestLifecycle.afterTestMethod();
        }
        
        logResult(result);
    }

    private void logResult(ITestResult result) {
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                logger.error("FAIL  {}", scenarioName.get());
                break;
            case ITestResult.SKIP:
                logger.warn("SKIP  {}", scenarioName.get());
                break;
            case ITestResult.SUCCESS:
                logger.info("PASS  {}", scenarioName.get());
                break;
            default:
                logger.warn("Unexpected result status: {}", result.getStatus());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
        UITestLifecycle.get().afterTestSuite();
    }
}
