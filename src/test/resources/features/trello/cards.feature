@wip
Feature: Set of scenarios to test the functionality of cards in Trello

  Background:
    Given I have successfully logged into trello

  
  Scenario: New card can be created
    Given I am on the trello test-board-1 board
    And I click 'Add another card' on the To Do column
    When I enter a title and click 'Add Card'
    Then the card will be created and visible


  Scenario: Selecting a card brings up the card detail window
    Given I am on the trello test-board-1 board
    And I select a card on the To Do column
    Then a window displaying edit options will appear
    And the name of my card will be displayed at the top of the window


  
  Scenario: Card can be edited and have a description added
    Given I am on the trello test-board-1 board
    And I select a card on the To Do column
    And I add a description to the card
    When I click 'Save' on the card detail window
    Then the 'Save' button will disappear from the edit window
    And a description icon will be displayed on the card


  
  Scenario: Checklist can be created on a card
    Given I am on the trello test-board-1 board
    And I add a checklist to a card
    When I add 3 items to the checklist
    Then the checklist items will be displayed on the card detail window in the unchcked state
    And the card on board will show the checklist icon with '0' items checked


  
  Scenario: Checkbox on a card checklist list can be checked
    Given I am on the trello test-board-1 board
    And I have a card with a checklist of 3 items all unchecked
    When I click the checkbox for the first item
    Then the checkbox will change to the checked state
    And the checklist progress bar will change to 33%
    And the card on board will show the checklist icon with '1' items checked


  
  Scenario: Checklist iterm can be converted into a card
    Given I am on the trello test-board-1 board
    And I select a card with a checklist
    When I convert a checklist item to a Card from the card detail window
    Then the item will be removed from the card
    And a new card will be created with the same title as the removed checklist item


  
  Scenario: Label can be added to a card
    Given I am on the trello test-board-1 board
    And I select a card on the To Do column
    When I add a 'blue' label to the card
    Then a blue label will be added to the card


  
  Scenario: Card can be dragged between lists on the board
    Given I am on the trello test-board-1 board
    And I have a card in the 'To Do' list
    When I drag a card from the 'To Do' column to the 'Doing' list
    Then the card will be shown in the 'Doing' list


  
  Scenario: Card can be moved to a different list from the card detail window
    Given I am on the trello test-board-1 board
    And I have a card in the 'Doing' list
    When I move the card from the 'To Do' column to the 'Doing' column in the card detail window
    Then the card will be shown in the 'Done' list
