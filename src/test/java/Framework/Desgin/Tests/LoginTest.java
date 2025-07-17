package Framework.Desgin.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.Desgin.TestComponents.Basetest;
import Framework.Design.PageObjects.Homepage;
import Framework.Design.PageObjects.LoginPage;

public class LoginTest extends Basetest {

	// This class will contain the test methods for the login functionality
	// It will use the LoginPage and Homepage classes to perform the tests

	 @Test(groups = {"smoke","positive tests"})
	 public void testValidLogin() {
	    
	     homepage = loginPage.Sign_in("standard_user", "secret_sauce");
	     Assert.assertTrue(homepage.isHomepageDisplayed(), "Homepage is not displayed after login");
	     
	 }
	
	// Negative test cases for login functionality
	 
	 @Test(groups = {"negative tests"})
	 public void loginWithInvalidUserName() {
	    
	     loginPage.Sign_in("invalid_user", "secret_sauce");
	     
	     String errorMessage = loginPage.getErrorMessage();
	     Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
	     
	 }
	 
	 @Test(groups = {"negative tests"})
	 public void loginWithlock_outUserCredentials() {
		    
	     loginPage.Sign_in("locked_out_user", "secret_sauce");
	     
	     String errorMessage = loginPage.getErrorMessage();
	     Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
	     
	 }
	 
	 @Test(groups = {"negative tests"})
	 public void loginWithInvalidPassword() {
		    
	     loginPage.Sign_in("standard_user", "invalid_password");
	     
	     String errorMessage = loginPage.getErrorMessage();
	     Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
	     
	 }
	 
	 @Test(groups = {"negative tests"})
	 public void loginWithEmptyCredentials() {
		 
		 loginPage.Sign_in("", "");
		 String errorMessage = loginPage.getErrorMessage();
		 Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
	 }
	 
	 @Test(groups = {"negative tests"})
	 public void loginWithEmptyUsername() {
		 
		 loginPage.Sign_in("", "secret_sauce");
		 String errorMessage = loginPage.getErrorMessage();
		 Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
	 }
	 
	 @Test(groups = {"negative tests"})
	 public void loginWithEmptyPassword() {
		 
		 loginPage.Sign_in("standard_user", "");
		 String errorMessage = loginPage.getErrorMessage();
		 Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
	 }
}
