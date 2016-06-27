package com.google.runner;

import com.frameworkium.core.ui.tests.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

public class SearchRunner extends BaseTest {

    @Test
    @CucumberOptions(features = "src/test/java/com/google/tests/web",
            glue = "com.google.glue",
            format = {"pretty"})
    private class RunTests extends AbstractTestNGCucumberTests {
    }

}