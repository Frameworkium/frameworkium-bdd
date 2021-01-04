package com.google.glue;

import com.google.pages.MapInfoPane;
import com.google.pages.MapPage;
import io.cucumber.java.en.*;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.google.common.truth.Truth.assertThat;

public class MapStepDefs {

    @Step
    @Given("I am on the google maps page")
    public void i_am_on_the_Google_Maps_page() {
        MapPage.open();
    }

    @Step
    @When("^I search for a location \"([^\"]*)\"$")
    public void i_search_for_a_location(String location) {
        new MapPage().get().search(location);
    }

    @Step
    @Then("^I should see information about \"([^\"]*)\"$")
    public void i_should_see_information_about(String searchTerm) {
        MapInfoPane mapInfoPaneWithTimeout =
                new MapInfoPane().get(Duration.ofSeconds(30));
        assertThat(mapInfoPaneWithTimeout.getInfoHeaderText())
                .contains(searchTerm);
    }

    @Step
    @And("^I should see the address \"([^\"]*)\"$")
    public void i_should_see_the_address(String address) {
        assertThat(new MapInfoPane().get().getAddress())
                .contains(address);
    }
}
