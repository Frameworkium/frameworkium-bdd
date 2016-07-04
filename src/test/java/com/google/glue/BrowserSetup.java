package com.google.glue;

import com.frameworkium.core.ui.tests.BaseTest;
import cucumber.api.java.Before;

public class BrowserSetup extends BaseTest {

    @Before
    public void configBrowser() {
        // This needs to be run once at the very start of any scenario
        configureBrowserBeforeUse();
    }
}
