package com.microsoft.glue;

import com.microsoft.pages.SupportHomePage;
import com.microsoft.pages.TemplatesPage;
import cucumber.api.java.en.*;

import static com.google.common.truth.Truth.assertThat;

public class MicrosoftStepDefs {

    @Given("^I am on the Microsoft Support homepage$")
    public void iAmOnTheMicrosoftSupportHomepage() {
        SupportHomePage.open();
    }

    @When("^I click the Templates Tab$")
    public void iClickTheTemplatesTab() {
        new SupportHomePage().get().clickTemplatesTab();
    }

    @Then("^the title '(.*)'$")
    public void theTitleOfficeTemplatesThemes(String title) {
        assertThat(new TemplatesPage().get().getTitle())
                .isEqualTo(title);
    }

}
