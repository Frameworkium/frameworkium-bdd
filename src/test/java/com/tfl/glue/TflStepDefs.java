package com.tfl.glue;

import static com.google.common.truth.Truth.assertWithMessage;

import com.tfl.api.dto.BikePoints;
import com.tfl.api.service.BikePointService;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.qatools.allure.annotations.Step;

public class TflStepDefs {

	private final BikePointService bikePoints;
	private BikePoints points;

	public TflStepDefs() {
		bikePoints = new BikePointService();
	}

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
