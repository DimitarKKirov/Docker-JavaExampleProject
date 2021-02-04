@Show @DB
Feature: example feature for database operations in Docker containers


  Scenario Outline: user inserts data in MySql database
    Given the user inserts data in Items table rows "<ItemSerialNumber>", "<ItemName>"
    When the user retrieves data
    Then the data is the same
    Examples:
      | ItemSerialNumber | ItemName |
      | 56874236         | item12   |


