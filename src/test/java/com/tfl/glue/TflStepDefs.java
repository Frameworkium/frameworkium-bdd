package com.tfl.glue;

import com.tfl.api.dto.BikePoints;
import com.tfl.api.service.BikePointService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import static com.google.common.truth.Truth.assertWithMessage;

public class TflStepDefs {

    private final BikePointService bikePoints = new BikePointService();
    private BikePoints points;

    @Step
    @When("^I request all bike points$")
    public void i_request_all_bike_points() {
        points = bikePoints.getAllBikePoints();
    }

    @Step
    @Then("^\"([^\"]*)\" will be in the result$")
    public void will_be_in_the_result(String name) {
        assertWithMessage("Expecting " + name + " to be in the search results")
                .that(points.getAllNames()).contains(name);
    }

}
