@wip
Feature: Set of scenarios to test the functionality of filtering a Trello board

  Background:
    Given I have successfully logged into trello

  Scenario: Menu is displayed when 'Show Menu' button is displayed
    Given I am on the trello labels board
    When I click the 'Show Menu' button
    Then the menu will be displayed

  Scenario: Cards can be filtered by label
    Given I am on the trello labels board
    When I filter cards from the menu by 'yellow label'
    Then 'Filtering is on' button is displayed
    And only cards with a yellow label will be displayed

  Scenario: Cards can be filtered by search term
    Given I am on the trello labels board
    When I filter cards from the menu by search term 'foo'
    Then 'Filtering is on' button is displayed
    And only cards with the title foo will be displayed

  Scenario: Cards can be filtered by both label and search term
    Given I am on the trello labels board
    When I filter cards from the menu by 'blue label'
    And I filter cards from the menu by search term 'bar'
    Then 'Filtering is on' button is displayed
    And only cards with the title foo and a blue label will be displayed


















