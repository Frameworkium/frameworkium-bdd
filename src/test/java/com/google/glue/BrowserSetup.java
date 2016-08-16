package com.google.glue;

import com.frameworkium.core.ui.tests.BaseTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Functionality for setup and teardown
 */
public class BrowserSetup extends BaseTest {

    Boolean driverExists = false;

    @Before
    public void setup() {
        // This needs to be run once at the very start of any scenario
        instantiateDriverObject();
        configureBrowserBeforeUse();
    }

    @After
    public void tearDown(Scenario scenario) {
        BaseTest.getDriver().quit();
    }

}
