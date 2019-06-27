import com.frameworkium.core.api.tests.BaseAPITest;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.*;
import org.testng.annotations.*;

@Test
@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features/"},
        tags = {"not @ignore and @api"},
        // NB: change these to match your glue packages.
        glue = {"com.google.glue", "com.tfl.glue", "org.seleniumhq.glue"})
public class APITestRunner extends BaseAPITest {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

    @Test(description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void feature(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        this.testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider
    public Object[][] scenarios() {
        return this.testNGCucumberRunner.provideScenarios();
    }

}
