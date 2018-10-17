package com.tfl.api.service;

import com.tfl.api.dto.BikePoints;
import com.tfl.api.dto.Place;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class BikePointService {

    private static final String URL = "https://api.tfl.gov.uk/BikePoint";

    public BikePoints getAllBikePoints() {
        final Place[] places = RestAssured.given()
                .contentType("application/json")
                .when()
                .request(Method.GET, URL)
                .then()
                .statusCode(200)
                .extract()
                .as(Place[].class);

        return new BikePoints(places);
    }

}
