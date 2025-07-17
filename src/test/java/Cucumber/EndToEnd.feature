Feature: Complete End-to-End Checkout Flow

  @regression
  Scenario Outline: End-to-End Purchase Flow
    When the user logs in with username "<username>" and password "<password>"
    And the user adds "<productName>" to the cart
    And the user navigates to the cart page
    Then the cart should contain "<productName>"
    When the user clicks on checkout
    And the user enters first name "<firstName>", last name "<lastName>", and postal code "<Zip/PostalCode>"
    And the user clicks on continue
    Then the checkout overview page should be displayed
    And the total price should be visible
    When the user clicks on finish
    Then the confirmation message "Thank you for your order!" should be displayed

  Examples:
    | username       | password      | productName           | firstName | lastName | Zip/PostalCode  |
    | standard_user  | secret_sauce  | Sauce Labs Backpack   | John      | Doe      | 12345           |
