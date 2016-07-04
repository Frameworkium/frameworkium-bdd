package com.google.tests.map;

import com.frameworkium.core.ui.tests.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

public class MapTest extends BaseTest {

    @Test
    @CucumberOptions(glue = "com.google.glue", format = {"pretty"})
    public class RunMapTests extends AbstractTestNGCucumberTests {
    }

}
