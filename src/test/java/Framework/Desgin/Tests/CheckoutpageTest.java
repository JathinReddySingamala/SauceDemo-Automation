package Framework.Desgin.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.Desgin.TestComponents.Basetest;
import Framework.Design.PageObjects.CartPage;
import Framework.Design.PageObjects.Checkoutpage1;
import Framework.Design.PageObjects.Checkoutpage2;
import Framework.Design.PageObjects.Checkoutpage3;
import Framework.Design.PageObjects.Homepage;

public class CheckoutpageTest extends Basetest {

	@Test(groups = {"smoke"})
	public void testCheckoutPage() 
	{
		// This method will contain the test logic for the checkout page
		Homepage homepage = loginPage.Sign_in("standard_user", "secret_sauce");
		
		// Add a products to the cart
		homepage.addProductToCart("Sauce Labs Backpack");
		CartPage cartpage =  homepage.clickOnCartHeader();
		
		Checkoutpage1 checkoutPage1 = cartpage.clickCheckout();
		checkoutPage1.fillCheckoutForm("John", "Doe", "12345");
		Checkoutpage2 checkoutpage2 = checkoutPage1.clickContinue();
		
		// Verify that the checkout page 2 is displayed
		Assert.assertTrue(checkoutpage2.isCheckoutPage2_Displayed().equalsIgnoreCase("Checkout: Overview"), 
				"Checkout page 2 is not displayed");
		
		//Printing the total price
		System.out.println("Total price " + checkoutpage2.getTotalPrice());
		
		Checkoutpage3 checkoutpage3 = checkoutpage2.clickFinish();
		
		System.out.println(checkoutpage3.getConfirmationMessage());
		
		checkoutpage3.clickBackToProducts();
		
	 }
		
}
