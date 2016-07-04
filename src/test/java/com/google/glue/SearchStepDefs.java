package com.google.glue;

import com.google.support.pages.HomePage;
import com.google.support.pages.ResultsPage;
import cucumber.api.java.en.*;
import ru.yandex.qatools.allure.annotations.Step;

import static com.google.common.truth.Truth.assertThat;

public class SearchStepDefs {

    @Step
    @Given("^I am on the google homepage$")
    public void I_am_on_the_google_homepage() {
        HomePage.open();
    }

    @Step
    @When("^I search for \"([^\"]*)\"$")
    public void search_for(String searchTerm) {
        new HomePage().get().runSearch(searchTerm);
    }

    @Step
    @Then("^I should see a link containing \"([^\"]*)\" on Results page$")
    public void should_see_on_results(String result) {
        assertThat(new ResultsPage().get()
                .getResultTitles()
                .anyMatch(title -> title.contains(result)))
                .isTrue();
    }

}
