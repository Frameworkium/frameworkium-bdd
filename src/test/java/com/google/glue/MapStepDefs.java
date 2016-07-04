package com.google.glue;

import com.google.support.pages.*;
import cucumber.api.java.en.*;
import ru.yandex.qatools.allure.annotations.Step;

import static com.google.common.truth.Truth.assertThat;

public class MapStepDefs {

    @Step
    @Given("^I open the maps app$")
    public void I_open_the_maps_app() {
        new HomePage().get().openMaps();
    }

    @Step
    @When("^I search for a location \"([^\"]*)\"$")
    public void i_search_for_a_location(String location) throws Throwable {
        new MapPage().get().search(location);
    }

    @Step
    @Then("^I should see information about \"([^\"]*)\"$")
    public void i_should_see_information_about(String searchTerm) throws Throwable {
        assertThat(new MapInfoPane().get().getHeader()).isEqualTo(searchTerm);
    }

    @Step
    @And("^I should see the address \"([^\"]*)\"$")
    public void i_should_see_the_address(String address) throws Throwable {
        assertThat(new MapInfoPane().get().getAddress()).isEqualTo(address);
    }
}
