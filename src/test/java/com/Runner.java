package com;


import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@Test
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"com.frameworkium.jira.listeners.CukesListenerV2"},
        monochrome = true,
        glue = {"com.google.glue"},
        tags = {"~@wip"})
//        tags = "@bug")
public class Runner extends AbstractTestNGCucumberTests {


}
