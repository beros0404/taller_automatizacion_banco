@customer @history
Feature: Transaction history and consecutive operations
  As a customer of the XYZ bank
  I want my consecutive operations to be reflected correctly in my balance and history
  So that I can trust the movements shown by the portal

  Background:
    Given Harry opens the bank portal
    And Harry logs in as customer "Harry Potter"
    And Harry records the initial balance of the account

  @happy_path
  Scenario: Two consecutive deposits accumulate in the balance and in the history
    When Harry deposits "100"
    And Harry deposits "50"
    Then the account balance increases by 150
    When Harry views the transaction history
    Then the transaction history lists 2 movements

  @alternate_path
  Scenario: Depositing and withdrawing the same amount restores the balance
    When Harry deposits "300"
    And Harry withdraws "300"
    Then the account balance stays the same

  @alternate_path
  Scenario: Resetting the history clears the listed movements
    When Harry deposits "100"
    And Harry views the transaction history
    Then the transaction history lists 1 movement
    When Harry resets the transaction history
    Then the transaction history lists 0 movements

  @exception_path
  Scenario: The withdrawal is not made when no amount is entered
    When Harry tries to withdraw without entering an amount
    Then the account balance stays the same
