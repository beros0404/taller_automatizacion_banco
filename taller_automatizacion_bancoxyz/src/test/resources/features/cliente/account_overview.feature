@customer @overview
Feature: Account overview and session of the customer
  As a customer of the XYZ bank
  I want to see my own information after logging in and be able to log out
  So that I know I am operating on my account and my session is closed safely

  @smoke @happy_path
  Scenario Outline: The welcome message shows the name of the customer who logged in
    Given Harry opens the bank portal
    And Harry logs in as customer "<customer>"
    Then the welcome message shows "<customer>"

    Examples:
      | customer          |
      | Harry Potter      |
      | Hermoine Granger  |
      | Albus Dumbledore  |
      | Neville Longbottom |

  @happy_path
  Scenario: The customer logs out and goes back to the login screen
    Given Harry opens the bank portal
    And Harry logs in as customer "Harry Potter"
    When Harry logs out
    Then the customer is back at the customer login screen
