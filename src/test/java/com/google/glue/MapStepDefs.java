package com.google.glue;

import com.google.pages.MapInfoPane;
import com.google.pages.MapPage;
import cucumber.api.java.en.*;
import ru.yandex.qatools.allure.annotations.Step;

import static com.google.common.truth.Truth.assertThat;

public class MapStepDefs {

    @Step
    @Given("^I am on the google maps page$")
    public void i_am_on_the_Google_Maps_page() throws Throwable {
        MapPage.open();
    }

    @Step
    @When("^I search for a location \"([^\"]*)\"$")
    public void i_search_for_a_location(String location) throws Throwable {
        new MapPage().get().search(location);
    }

    @Step
    @Then("^I should see information about \"([^\"]*)\"$")
    public void i_should_see_information_about(String searchTerm) throws Throwable {
        final MapInfoPane mapInfoPaneWithTimeout = new MapInfoPane().get(30);
        assertThat(mapInfoPaneWithTimeout.getHeader()).isEqualTo(searchTerm);
    }

    @Step
    @And("^I should see the address \"([^\"]*)\"$")
    public void i_should_see_the_address(String address) throws Throwable {
        assertThat(new MapInfoPane().get().getAddress()).isEqualTo(address);
    }
}
