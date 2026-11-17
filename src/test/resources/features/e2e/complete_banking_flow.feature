@e2e
Feature: Complete banking flow end to end
  As the XYZ bank
  I want a new customer to be created, receive an account and operate on it
  So that the whole business cycle is verified as one integrated flow

  @smoke @happy_path
  Scenario: The manager creates a customer with an account and the customer deposits and withdraws
    Given Marta opens the bank portal
    And Marta logs in as bank manager
    When Marta adds the customer "Laura" "Gomez" with post code "050021"
    And Marta opens a "Dollar" account for the customer "Laura Gomez"
    And Marta views the customers list
    Then the customers list includes "Laura Gomez"
    When Marta goes back to the home screen
    And Marta logs in as customer "Laura Gomez"
    And Marta deposits "3000"
    And Marta withdraws "1200"
    Then the account balance is 1800
    When Marta views the transaction history
    Then the transaction history lists 2 movements
