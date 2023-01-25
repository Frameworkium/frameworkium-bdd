@ignore

Feature: Fetching bike points

  In order to find some information
  As a searcher
  I want to be able to search and have relevant information returned

  @api
  Scenario Outline: Get all bike points contains <name>
    When I request all bike points
    Then "<name>" will be in the result

  Examples:
    | name                       |
    | Evesham Street, Avondale   |
    | River Street , Clerkenwell |
