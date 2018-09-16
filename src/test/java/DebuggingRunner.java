import com.frameworkium.core.ui.tests.BaseUITest;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

@Test
@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features/"},
        plugin = {"ru.yandex.qatools.allure.cucumberjvm.AllureReporter",
                "com.frameworkium.core.common.listeners.CucumberZephyrListener"},
        monochrome = true,
        tags = {"~@ignore"},
        glue = {"com.google.glue", "com.tfl.glue", "com.microsoft.glue"})
public class DebuggingRunner extends BaseUITest {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

    @Test(groups = {"cucumber"},
            description = "Runs Cucumber Feature",
            dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        this.testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return this.testNGCucumberRunner.provideFeatures();
    }

}
