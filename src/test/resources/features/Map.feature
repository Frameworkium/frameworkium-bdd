@TestCaseId(TEST-1) @ui
Feature: Map Search

  In order to find information about places
  As a *curious* person
  I want to be able to get information about places I search for

  Background:
    Given I am on the google maps page

  @Stories(one_story,another_story)
  @Story(Another_sodding_story)
  Scenario: Search for BlueFin
    When I search for a location "Blue Fin Venue"
    Then I should see information about "Blue Fin Venue"
    And I should see the address "110 Southwark St, London SE1 0SU"
