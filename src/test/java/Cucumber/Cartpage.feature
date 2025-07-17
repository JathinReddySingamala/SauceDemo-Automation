Feature: Cart Page functionality

  Background:
    When the user logs in with username "standard_user" and password "secret_sauce"
    And the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user adds "Sauce Labs Fleece Jacket" to the cart
    And the user navigates to the cart page

  @smoke
  Scenario: Verify cart operations
    Then the cart should contain "Sauce Labs Backpack"
    And the cart should contain "Sauce Labs Bike Light"
    And the cart should contain "Sauce Labs Fleece Jacket"
    When the user removes "Sauce Labs Backpack" from the cartlist
    Then the cart should not contain "Sauce Labs Backpack"
    And the total cart price should be "$59.98"
    When the user clicks on checkout
    Then the checkout page should be displayed
