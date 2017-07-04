import java.util.Collection;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.frameworkium.core.ui.tests.BaseTest;
import com.google.common.collect.Lists;

import cucumber.api.testng.CucumberFeatureWrapperImpl;
import cucumber.runtime.CucumberException;
import cucumber.runtime.model.CucumberFeature;
import cucumber.runtime.model.CucumberTagStatement;
import gherkin.formatter.model.Tag;

/** Functionality for setup */
public class BrowserSetup implements ITestListener {

	/** Required to replicate the @BeforeMethod in {@link BaseTest}. */
	@Override
	public void onTestStart(ITestResult iTestResult) {
		if (!getTags(iTestResult).contains("@api")) {
			BaseTest.instantiateDriverObject();
			BaseTest.configureBrowserBeforeTest(
					iTestResult.getInstance().getClass().getCanonicalName());
		}
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		if (!getTags(iTestResult).contains("@api")) {
			BaseTest.tearDownBrowser();
		}
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		final Throwable throwable = iTestResult.getThrowable();
		if (throwable instanceof CucumberException) {
			iTestResult.setThrowable(throwable.getCause());
		}

		if (!getTags(iTestResult).contains("@api")) {
			BaseTest.tearDownBrowser();
		}
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		if (!getTags(iTestResult).contains("@api")) {
			BaseTest.tearDownBrowser();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		if (!getTags(iTestResult).contains("@api")) {
			BaseTest.tearDownBrowser();
		}
	}

	@Override
	public void onStart(ITestContext iTestContext) {
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
	}

	private Collection<String> getTags(ITestResult iTestResult) {
		for (final Object property : iTestResult.getParameters()) {
			if (property instanceof CucumberFeatureWrapperImpl) {
				final CucumberFeatureWrapperImpl prop = (CucumberFeatureWrapperImpl) property;
				final CucumberFeature feature = prop.getCucumberFeature();

				final List<String> returnValue = Lists.newArrayList();
				for (final CucumberTagStatement statement : feature.getFeatureElements()) {
					for (final Tag tag : statement.getGherkinModel().getTags()) {
						returnValue.add(tag.getName());
					}
				}

				return returnValue;
			}
		}

		throw new IllegalStateException("Could not get tags");
	}

}