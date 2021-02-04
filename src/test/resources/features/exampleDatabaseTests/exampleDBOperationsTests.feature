@Show
Feature: example feature for database operations in Docker containers

  @DB
  Scenario Outline: user inserts data in MySql database
    Given the user inserts data in Items table rows "<ItemSerialNumber>", "<ItemName>"
    When the user retrieves data
    Then the data is the same
    Examples:
      | ItemSerialNumber | ItemName |
      | 56874236         | item12   |


  @DB
  Scenario: Comparing data from two data bases (Mysql,Postgres)
    Given the two data bases are not empty
    When the data of the two databases is compared
    Then the two data bases are holding the same data