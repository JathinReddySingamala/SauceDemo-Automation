
Feature: Home Page Functionality
  
   Background:
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the homepage should be displayed
    
  @smoke
  Scenario: Add and remove products from the cart
    Then the product list should be visible
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    Then the cart badge should show "2"
    When the user removes "Sauce Labs Backpack" from the cart
    Then the cart badge should show "1"

  @positive
  Scenario: Logout from the homepage
    When the user clicks on the menu item "Logout"
    Then the user should be redirected to the login page
    

