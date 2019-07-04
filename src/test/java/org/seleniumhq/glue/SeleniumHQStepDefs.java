package org.seleniumhq.glue;

import org.seleniumhq.pages.SeleniumDownloadPage;
import org.seleniumhq.pages.SeleniumHomePage;
import io.cucumber.java.en.*;
import io.qameta.allure.Step;

import static com.google.common.truth.Truth.assertThat;

public class SeleniumHQStepDefs {

    @Step
    @Given("^I am on the SeleniumHQ home page$")
    public void open() {
        SeleniumHomePage.open();
    }

    @Step
    @When("^I click downloads$")
    public void i_click_downloads() {
        new SeleniumHomePage().get().gotoDownloadsPage();
    }

    @Step
    @Then("^I should see a link containing the latest version$")
    public void i_should_see_the_latest_version() {
        String version = new SeleniumDownloadPage().get().getLatestVersion();
        assertThat(version).matches("\\d\\.\\d+\\.\\d+");
    }

}
