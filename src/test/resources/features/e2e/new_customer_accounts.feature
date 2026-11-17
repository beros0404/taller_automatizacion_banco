@e2e @accounts
Feature: Accounts of a customer created by the manager
  As the XYZ bank
  I want the accounts opened by the manager to be what the customer sees
  So that the currency, the starting balance and the number of accounts are correct

  @happy_path
  Scenario Outline: A new account starts with zero balance in the chosen currency
    Given Marta opens the bank portal
    And Marta logs in as bank manager
    When Marta adds the customer "Laura" "Gomez" with post code "050021"
    And Marta opens a "<currency>" account for the customer "Laura Gomez"
    And Marta goes back to the home screen
    And Marta logs in as customer "Laura Gomez"
    Then the account balance is 0
    And the account currency is "<currency>"

    Examples:
      | currency |
      | Dollar   |
      | Pound    |
      | Rupee    |

  @alternate_path
  Scenario: A customer without accounts is asked to open one
    Given Marta opens the bank portal
    And Marta logs in as bank manager
    When Marta adds the customer "Laura" "Gomez" with post code "050021"
    And Marta goes back to the home screen
    And Marta logs in as customer "Laura Gomez"
    Then the portal tells the customer to open an account first

  @alternate_path
  Scenario: A customer with two accounts can choose between them
    Given Marta opens the bank portal
    And Marta logs in as bank manager
    When Marta adds the customer "Laura" "Gomez" with post code "050021"
    And Marta opens a "Dollar" account for the customer "Laura Gomez"
    And Marta opens a "Pound" account for the customer "Laura Gomez"
    And Marta goes back to the home screen
    And Marta logs in as customer "Laura Gomez"
    Then the account selector lists 2 accounts
