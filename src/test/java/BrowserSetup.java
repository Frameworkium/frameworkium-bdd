import com.frameworkium.core.ui.tests.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

/** Functionality for setup */
public class BrowserSetup implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        BaseTest.configureBrowserBeforeTest(getMethod(iTestResult));
    }

    private Method getMethod(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getMethod();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        closeBrowser();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        closeBrowser();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        closeBrowser();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        closeBrowser();
    }

    private void closeBrowser() {
        try {
            BaseTest.getDriver().quit();
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
