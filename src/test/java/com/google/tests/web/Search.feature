Feature: Search section

  Scenario: Search for Hello world
    Given I am on the google homepage
    When I search for "hello world"
    Then I should see a link containing "HelloWorld" on Results page