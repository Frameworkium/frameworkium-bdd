@Story:feature_level_story
Feature: Web Search2

  Scenario: Search for Hello world
    Given I am on the google homepage
    When I search for "hello world"
    Then I should see a link containing "HelloWorld" on Results page

  @Story:scen_level_story
  Scenario: Search for Cucumber
    Given I am on the google homepage
    When I search for "Cucumber"
    Then I should see a link containing "Cucumber" on Results page
