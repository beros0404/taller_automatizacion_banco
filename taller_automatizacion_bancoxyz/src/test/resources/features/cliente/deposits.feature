@customer @deposits
Feature: Deposits into the customer account
  As a customer of the XYZ bank
  I want to deposit money into my account
  So that my available balance grows

  Background:
    Given Harry opens the bank portal
    And Harry logs in as customer "Harry Potter"
    And Harry records the initial balance of the account

  @smoke @happy_path
  Scenario Outline: Successful deposit with different amounts including limits
    When Harry deposits "<amount>"
    Then the portal shows the message "Deposit Successful"
    And the account balance increases by <amount>

    Examples:
      | amount |
      | 1      |
      | 100    |
      | 99999  |

  @exception_path
  Scenario: The deposit is not made when no amount is entered
    When Harry tries to deposit without entering an amount
    Then the account balance stays the same

  @alternate_path
  Scenario: The deposit is recorded in the transaction history
    When Harry deposits "250"
    And Harry views the transaction history
    Then the transaction history lists 1 movement
