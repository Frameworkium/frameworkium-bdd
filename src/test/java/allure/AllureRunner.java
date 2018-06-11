package allure;

import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@Test
@CucumberOptions(
        features = {"src/test/resources/features"},
//        plugin = {"listeners.AllureListenerCukes3"},
        plugin = {"io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm"},
        monochrome = true,
        glue = {"allure"},
        tags = {"~@ui"})
public class AllureRunner extends AbstractTestNGCucumberTests {


}
