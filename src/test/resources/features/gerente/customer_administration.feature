@manager @administration
Feature: Customer administration by the bank manager
  As the bank manager
  I want to remove customers and find them by their data
  So that the customer base only contains valid customers

  Background:
    Given Marta opens the bank portal
    And Marta logs in as bank manager

  @happy_path
  Scenario: Delete an existing customer
    When Marta deletes the customer "Neville Longbottom"
    Then the customers list shows 4 results
    And the customers list does not include "Neville Longbottom"

  @alternate_path
  Scenario Outline: Search customers by account number
    When Marta searches the customers list for "<account>"
    Then the customers list shows <results> results

    Examples:
      | account | results |
      | 1004    | 1       |
      | 1001    | 1       |
