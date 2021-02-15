@Show @SeleniumGrid
Feature: selenium grid example, instantiation of selenium grind from docker-compose file with sending command to
  windows command line. Read the hooks method description for more details.


  Scenario: Chrome test for Lilly web shop - navigating to login page and log in
    Given open Home Page "https://shop.lillydrogerie.bg/"
    When  click on Вход button
    Then you are redirected to the login page of Lilly website

  Scenario Outline: Chrome test for Lilly web shop - user is logged in with correct credentials
    Given the user is on the "<Login>" page
    When user enters the correct "<username>" and "<password>"
    And clicks on button Вход
    Then user is successfully logged in
    Examples:
      | username                     | password    | Login                                                                                                  |
      | Dimitar.testing.bg@gmail.com | iskamneshto | https://shop.lillydrogerie.bg/customer/account/login/referer/aHR0cHM6Ly9zaG9wLmxpbGx5ZHJvZ2VyaWUuYmcv/ |

  Scenario Outline: FireFox test for Lilly web shop - user goes to Home Products from the left menu
    Given the user is on the "<home page>"
    And the user is logged in with "<email>" and "<pass>"
    When the user clicks Home products from the left had menu
    Then the user is redirected to the shop list with the corresponding items
    Examples:
      | home page                      | email                        | pass        |
      | https://shop.lillydrogerie.bg/ | Dimitar.testing.bg@gmail.com | iskamneshto |


  Scenario: FireFox test for Lilly web shop - Adding items to the shopping cart and opening the shopping cart
    Given user is in Home products "https://shop.lillydrogerie.bg/"
    And the user clicks Home products from the left had menu
    And add items to the basket
    And the user clicks the basket
    When the total price is calculated correctly for the items in the basket
    Then the user is redirected to the shopping cart
