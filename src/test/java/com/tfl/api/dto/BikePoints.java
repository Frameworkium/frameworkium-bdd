package com.tfl.api.dto;

import io.qameta.allure.Step;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class BikePoints {

    private final List<Place> bikePoints;

    public BikePoints(Place[] places) {
        bikePoints = Arrays.asList(places);
    }

    @Step
    public List<String> getAllNames() {
        return bikePoints.stream()
                .map(bp -> bp.commonName)
                .collect(Collectors.toList());
    }

}