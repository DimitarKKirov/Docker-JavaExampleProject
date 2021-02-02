@Show @Selenium
Feature: example feature for selenium testing with Docker containers


  Scenario: user searching in google
    Given the user is on "https://www.emag.bg/"
    When tha user searches for "windows"
    Then the user finds relevant result equal to "39 резултата Операционни системи за \"windows\""