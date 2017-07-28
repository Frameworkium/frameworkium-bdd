package com.tfl.glue;

import com.tfl.web.JourneyPlanDetails;
import com.tfl.api.dto.BikePoints;
import com.tfl.api.service.BikePointService;
import com.tfl.web.pages.HomePage;
import com.tfl.web.pages.JourneyPlannerResultsPage;
import com.tfl.web.pages.PlanJourneyPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

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

	@Step
    @Given("^I am on the TFL 'plan a journey' page$")
    public void iAmOnTheTFLPlanAJourneyPage() throws Throwable {
        HomePage.open().clickPlanJourneyLink();
    }

	@Step
    @Then("^I will be on the Journey Results page$")
    public void iWillBeOnTheJourneyResultsPage() throws Throwable {

        assertThat(new JourneyPlannerResultsPage().get().getTitleText()).isEqualTo("JOURNEY RESULTS");
    }

    @Step
    @When("^I plan a journey with the following data:$")
    public void iPlanAJourneyWithTheFollowingData(List<JourneyPlanDetails> journeyPlanDetails) throws Throwable {
        new PlanJourneyPage().get().planJourney(
                journeyPlanDetails.get(0).getSourceDestination(),
                journeyPlanDetails.get(0).getTargetDestination());
    }
}
