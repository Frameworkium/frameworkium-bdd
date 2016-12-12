import com.frameworkium.core.ui.tests.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Functionality for setup */
public class BrowserSetup implements ITestListener {

    /** Required to replicate the @BeforeMethod in {@link BaseTest}. */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        BaseTest.configureBrowserBeforeTest(
                iTestResult.getInstance().getClass().getCanonicalName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
