@Show @DBComparison
Feature: comparison between two databases

  Scenario: Comparing data from two databases (Mysql,Postgres)
    Given the two databases are not empty
    When the data of the two databases is compared
    Then the two data bases are holding the same data