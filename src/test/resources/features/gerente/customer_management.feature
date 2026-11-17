@manager
Feature: Customer and account management by the bank manager
  As the bank manager
  I want to add customers, open accounts for them and look them up
  So that the customer base of the bank stays up to date

  Background:
    Given Marta opens the bank portal
    And Marta logs in as bank manager

  @smoke @happy_path
  Scenario: Add a new bank customer
    When Marta adds the customer "Camila" "Restrepo" with post code "050001"
    Then the system shows the alert "Customer added successfully"

  @exception_path
  Scenario: A duplicated customer is not accepted
    When Marta adds the customer "Camila" "Restrepo" with post code "050001"
    And Marta adds the customer "Camila" "Restrepo" with post code "050001"
    Then the system shows the alert "Please check the details. Customer may be duplicate."

  @exception_path
  Scenario: A customer is not registered when the form is empty
    When Marta tries to add a customer without filling in the form
    Then the system shows no alert

  @smoke @happy_path
  Scenario Outline: Open an account in different currencies for an existing customer
    When Marta opens a "<currency>" account for the customer "Harry Potter"
    Then the system shows the alert "Account created successfully"

    Examples:
      | currency |
      | Dollar   |
      | Pound    |
      | Rupee    |

  @happy_path
  Scenario: List the registered customers
    When Marta views the customers list
    Then the customers list includes "Hermoine Granger"
    And the customers list includes "Harry Potter"

  @alternate_path
  Scenario Outline: Search customers by name in the list
    When Marta searches the customers list for "<text>"
    Then the customers list shows <results> results

    Examples:
      | text     | results |
      | Hermoine | 1       |
      | Potter   | 1       |
      | Zzzzzz   | 0       |
