Feature: Can find Star Wars People Using API

  Background: API allows us to access people

  We want the to make sure some common people are in the API

  Scenario: Get Luke from the API
    Given a user ID "1"
    When a call to the "people" api is made
    Then the name of the person is "Luke Skywalker"


  Scenario: Get C3PO from the API
    Given a user ID "2"
    When a call to the "people" api is made
    Then the name of the person is "C-3PO"


  Scenario Outline:

    * Users exist with the following "<userid>" and "<name>"

    Examples:
      | userid | name           |
      | 1      | Luke Skywalker |
      | 2      | C-3PO          |
      | 3      | R2-D2          |