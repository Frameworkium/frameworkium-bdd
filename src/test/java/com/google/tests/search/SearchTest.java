package com.google.tests.search;

import com.frameworkium.core.ui.tests.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    @CucumberOptions(glue = "com.google.glue", format = {"pretty"})
    public class RunSearchTests extends AbstractTestNGCucumberTests {
    }

}
