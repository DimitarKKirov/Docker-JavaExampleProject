@Show @Rest
Feature: GoApi end points

  Scenario Outline: Docker GoApi
    Given user connects to api using url "<point>"
    When request for end "<point>"
    Then the response body is available
    Examples:
      | point  |
      | http://localhost:8083/greeting |