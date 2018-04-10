@angular
Feature: A test to test automation on an Angular webpage

  Scenario: Navigate across 2 Angular webpages
    Given I am on the Microsoft Support homepage
    When I click the Templates Tab
    Then the title 'Office templates & themes'