package com.frameworkium.bdd;

import com.frameworkium.core.ui.UITestLifecycle;
import com.frameworkium.core.ui.listeners.CaptureListener;
import com.frameworkium.core.ui.listeners.ScreenshotListener;
import gherkin.events.PickleEvent;
import io.cucumber.testng.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners({CaptureListener.class, ScreenshotListener.class})
@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features/"},
        tags = {"not @ignore and not @api"},
        plugin = {
                "pretty", // pretty console logging
                "json:cucumber-results.json" // json results file
        },
        // NB: change these to match your glue packages.
        glue = {"com.google.glue", "org.seleniumhq.glue"})
public class UITestRunner implements ITest {

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
        PickleEvent pickleEvent = ((PickleEventWrapper) testData[0]).getPickleEvent();
        scenarioName.set(pickleEvent.pickle.getName());
        logger.info("START {}", scenarioName.get());
        UITestLifecycle.get().beforeTestMethod(scenarioName.get());
    }

    @Test(dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cfw) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        UITestLifecycle.get().afterTestMethod();
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

    @Override
    public String getTestName() {
        return scenarioName.get();
    }
}
