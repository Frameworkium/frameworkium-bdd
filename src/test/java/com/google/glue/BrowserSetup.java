package com.google.glue;

import com.frameworkium.core.ui.tests.BaseTest;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/** Functionality for setup */
public class BrowserSetup extends BaseTest {

    @Before
    public void setup() {
        // This needs to be run once at the very start of any scenario
        instantiateDriverObject();
        configureBrowserBeforeUse();
    }

    @After
    public void tearDown() {
        try {
            BaseTest.getDriver().quit();
        } catch (Exception ignored) {
        }
    }

}
