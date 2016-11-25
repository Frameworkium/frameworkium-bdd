package com.google.glue;

import com.frameworkium.core.ui.tests.BaseTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.testng.annotations.AfterTest;

/**
 * Functionality for setup and teardown
 */
public class BrowserSetup extends BaseTest {

    @Before
    public void setup() {
        try {
            BaseTest.getDriver().quit();
        } catch (Exception e) {}
        // This needs to be run once at the very start of any scenario
        instantiateDriverObject();
        configureBrowserBeforeUse();
    }

    @AfterTest
    public void tearDown() {
        BaseTest.getDriver().quit();
    }

}
