Feature: Checkout functionality

  Background:
    When the user logs in with username "standard_user" and password "secret_sauce"
    And the user adds "Sauce Labs Backpack" to the cart
    And the user navigates to the cart page
    And the user clicks on checkout

  @smoke
  Scenario: Complete a successful checkout
    When the user enters first name "John", last name "Doe", and postal code "12345"
    And the user clicks on continue
    Then the checkout overview page should be displayed
    And the total price should be visible
    When the user clicks on finish
    Then the confirmation message "Thank you for your order!" should be displayed
    And the user clicks on back to products
