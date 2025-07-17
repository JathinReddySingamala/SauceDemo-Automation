
Feature: Login Functionality
  

  @smoke @positive
  Scenario Outline: Valid Login
    When the user logs in with username "<username>" and password "<password>"
    Then the homepage should be displayed

    Examples: 
      | username      | password     | 
      | standard_user | secret_sauce | 
     

  @negative
  Scenario Outline: Login with invalid or empty credentials
    
    When the user logs in with username "<username>" and password "<password>"
    Then the error message should be "<error>"

    Examples:
      | username        | password       | error                                                                     |
      | invalid_user    | secret_sauce   | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce   | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user   | invalid_pass   | Epic sadface: Username and password do not match any user in this service |
      |                 |                | Epic sadface: Username is required                                        |
      |                 | secret_sauce   | Epic sadface: Username is required                                        |
      | standard_user   |                | Epic sadface: Password is required                                        |
