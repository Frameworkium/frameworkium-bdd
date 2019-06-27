import com.frameworkium.core.ui.tests.BaseUITest;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.*;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

/**
 * https://github.com/cucumber/cucumber-jvm/tree/v4.0.0/testng
 * https://www.swtestacademy.com/change-test-name-testng-dataprovider/
 */
@Test
@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features/"},
        tags = {"not @ignore and not @api"},
        // NB: change these to match your glue packages.
        glue = {"com.google.glue", "com.tfl.glue", "org.seleniumhq.glue"})
public class UITestRunner extends BaseUITest implements ITest {

    private TestNGCucumberRunner testNGCucumberRunner;
    /** Stores a custom test name */
    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

    @Test(description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cfw) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    /** Set the test name. */
    @BeforeMethod
    public void beforeMethod(Method method, Object[] testData) {
        testName.set(((PickleEventWrapper) testData[0]).getPickleEvent().pickle.getName());
    }

    @BeforeMethod
    public void beforeMethod(Method method, Object[] testData, ITestContext ctx) {
        if (testData.length > 0) {
            testName.set(method.getName() + "_" + testData[0]);
            ctx.setAttribute("testName", testName.get());
        } else {
            ctx.setAttribute("testName", method.getName());
        }
        //throw new RuntimeException("called beforeMethod with TestContext");

    }

    /** @return Get the test name (works for IntelliJ etc.) */
    @Override
    public String getTestName() {
        return testName.get();
    }


}
