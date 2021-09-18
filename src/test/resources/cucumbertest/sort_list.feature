Feature: Sort list
  I want to sort a list of integers

  Scenario: Empty list
    Given a list with []
    When I sort the list
    Then the list must be []

  Scenario: Singleton list
    Given a list with [1]
    When I sort the list
    Then the list must be [1]

  Scenario: Two elements list
    Given a list with [3,1]
    When I sort the list
    Then the list must be [1,3]

  Scenario: Large list
    Given a list with [2,34,5,6,7,643,3,53,23234,6,241,0,32]
    When I sort the list
    Then the list must be [0,2,3,5,6,6,7,32,34,53,241,643,23234]