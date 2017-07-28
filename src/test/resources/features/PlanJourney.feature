@datatable
Feature: TFL Journey Planer Tests

  Scenario: Plan a journey test
    Given I am on the TFL 'plan a journey' page
    When I plan a journey with the following data:
      | sourceDestination | targetDestination                 |
      | Clapham Junction  | Oxford Circus Underground Station |
    Then I will be on the Journey Results page
