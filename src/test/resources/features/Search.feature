@Story(feature_level_story)
Feature: Web Search

  In order to find some information
  As a searcher
  I want to be able to search and have relevant information returned

  Background:
    Given I am on the google homepage

  Scenario: Search for Hello world
    When I search for "hello world"
    Then I should see a link containing "HelloWorld" on Results page

  @Story(scen_level_story)
  Scenario: Search for Cucumber
    When I search for "Cucumber"
    Then I should see a link containing "Cucumber" on Results page
