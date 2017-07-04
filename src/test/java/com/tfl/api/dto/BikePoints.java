package com.tfl.api.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.yandex.qatools.allure.annotations.Step;

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