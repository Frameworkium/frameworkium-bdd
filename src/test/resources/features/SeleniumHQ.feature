@Story(feature_level_story) @all
Feature: SeleniumHQ Download

  In order to do my job
  As a someone who wants to automate the browser
  I want to be able to see the latest selenium version

  Background:
    Given I am on the SeleniumHQ home page

  @CustomTag
  Scenario: Find the version
    When I click downloads
    Then I should see a link containing the latest version
