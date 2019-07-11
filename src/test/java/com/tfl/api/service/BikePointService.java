package com.tfl.api.service;

import com.tfl.api.dto.BikePoints;
import com.tfl.api.dto.Place;
import io.restassured.RestAssured;

public class BikePointService {

    private static final String URL = "https://api.tfl.gov.uk/BikePoint";

    public BikePoints getAllBikePoints() {
        Place[] places = RestAssured.given()
                .contentType("application/json")
                .when()
                .get(URL)
                .then()
                .statusCode(200)
                .extract()
                .as(Place[].class);

        return new BikePoints(places);
    }

}
