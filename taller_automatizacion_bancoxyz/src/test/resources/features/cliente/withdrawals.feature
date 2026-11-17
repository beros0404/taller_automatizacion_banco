@customer @withdrawals
Feature: Withdrawals from the customer account
  As a customer of the XYZ bank
  I want to withdraw money from my account
  So that I have cash without exceeding my balance

  Background:
    Given Harry opens the bank portal
    And Harry logs in as customer "Harry Potter"
    And Harry has at least 1000 in the account

  @smoke @happy_path
  Scenario Outline: Successful withdrawal with enough balance
    When Harry withdraws "<amount>"
    Then the portal shows the message "Transaction successful"
    And the account balance decreases by <amount>

    Examples:
      | amount |
      | 1      |
      | 500    |
      | 1000   |

  @alternate_path @limit
  Scenario: Withdrawing the entire balance leaves the account at zero
    When Harry withdraws the entire balance
    Then the account balance is 0

  @smoke @exception_path @limit
  Scenario: A withdrawal greater than the balance is rejected
    When Harry tries to withdraw more than the balance
    Then the portal shows the message "Transaction Failed. You can not withdraw amount more than the balance."
    And the account balance stays the same
