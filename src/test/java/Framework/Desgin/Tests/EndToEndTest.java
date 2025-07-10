package Framework.Desgin.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.Desgin.TestComponents.Basetest;
import Framework.Design.PageObjects.CartPage;
import Framework.Design.PageObjects.Checkoutpage1;
import Framework.Design.PageObjects.Checkoutpage2;
import Framework.Design.PageObjects.Checkoutpage3;
import Framework.Design.PageObjects.Homepage;

public class EndToEndTest extends Basetest {

	@Test(dataProvider = "getData",groups = {"regression"})
	public void CompleteEndToEndTest(HashMap<String, String> input) {
		
		// This test will perform a complete end-to-end scenario
		//Step:1 Login to the application
		Homepage homepage = loginPage.Sign_in(input.get("username"), input.get("password"));
		
		//Step:2 Add products to the cart
		homepage.addProductToCart(input.get("productName"));
		
		//Step:3 Go to the cart page
		CartPage cartpage = homepage.clickOnCartHeader();
		//Verify the product is added to the cart
		Assert.assertTrue(cartpage.verifyProductInCart(input.get("productName")),
				"Product 'Sauce Labs Backpack' is not present in the cart");
		
		//Step:4 Click on the checkout button and go to the checkout page
		Checkoutpage1 checkoutpage1 = cartpage.clickCheckout();
		//Fill the checkout form
		checkoutpage1.fillCheckoutForm(input.get("firstName"), input.get("lastName"), input.get("Zip/PostalCode"));
		
		//Step:5 Click on the continue button and go to the checkout page 2
		Checkoutpage2 checkoutpage2 = checkoutpage1.clickContinue();
		//Verify that the checkout page 2 is displayed
		Assert.assertTrue(checkoutpage2.isCheckoutPage2_Displayed().equalsIgnoreCase("Checkout: Overview"), 
				"Checkout page 2 is not displayed");
		System.out.println("Total price " + checkoutpage2.getTotalPrice());
		
		//Step:6 Click on the finish button and go to the confirmation page
		Checkoutpage3 checkoutpage3 = checkoutpage2.clickFinish();
		//Verify the confirmation message
		Assert.assertTrue(checkoutpage3.getConfirmationMessage().equals("Thank you for your order!"),
				"Confirmation message is not displayed");
		
		
	}
	
	@DataProvider(name = "getData")
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"//src//main//java//Resources//TestData.json");
		return new Object[][] {{data.get(0)}};
	}
}
