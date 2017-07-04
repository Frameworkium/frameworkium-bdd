Feature: Fetching bike points

  In order to find some information
  As a searcher
  I want to be able to search and have relevant information returned

  @api
  Scenario Outline: Get all bike points
    When I request all bike points
    Then "<the name>" will be in the result

  Examples:
    | the name                    |
    | Evesham Street, Avondale    |
    | River Street , Clerkenwell  |
