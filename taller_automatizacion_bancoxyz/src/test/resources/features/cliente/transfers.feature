@customer @transfers
Feature: Transfers between accounts of the same customer
  As a customer of the XYZ bank with several accounts
  I want to move money from one account to another
  So that I can organize my funds
  # Note: the portal has no native transfer option. A transfer is modelled as
  # a withdrawal in the origin account plus a deposit in the destination account.

  Background:
    Given Harry opens the bank portal
    And Harry logs in as customer "Harry Potter"
    And Harry has at least 1000 in the account

  @happy_path
  Scenario: Successful transfer between two own accounts
    When Harry transfers 500 from account "1004" to account "1005"
    Then both account balances reflect the transfer of 500

  @exception_path
  Scenario: Transfer rejected because of insufficient funds
    When Harry tries to transfer more than the balance from account "1004" to account "1005"
    Then the transfer is rejected and both account balances stay the same
