import com.frameworkium.core.ui.tests.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

/** Functionality for setup */
public class BrowserSetup implements ITestListener {

    /** Required to replicate the @BeforeMethod in {@link BaseTest}. */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        BaseTest.configureBrowserBeforeTest(getMethod(iTestResult));
    }

    private Method getMethod(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getMethod();
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
