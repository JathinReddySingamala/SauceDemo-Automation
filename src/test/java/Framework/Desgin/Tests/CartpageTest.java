package Framework.Desgin.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.Desgin.TestComponents.Basetest;
import Framework.Design.PageObjects.CartPage;
import Framework.Design.PageObjects.Homepage;

public class CartpageTest extends Basetest {

	@Test(groups = {"smoke"})
	public void testCartPage() {
		// This method will contain the test logic for the cart page
		// It will use the Homepage class to interact with the cart functionality
		
		Homepage homepage = loginPage.Sign_in("standard_user", "secret_sauce");
		
		// Add a products to the cart
		homepage.addProductToCart("Sauce Labs Backpack");
		Assert.assertEquals(homepage.Added_to_Cart(), "1", "Product was not added to cart successfully");
		homepage.addProductToCart("Sauce Labs Bike Light");
		Assert.assertTrue(homepage.Added_to_Cart().equalsIgnoreCase("2"), "Product was not added to cart successfully");
		homepage.addProductToCart("Sauce Labs Fleece Jacket");
		Assert.assertTrue(homepage.Added_to_Cart().equalsIgnoreCase("3"), "Product was not added to cart successfully");
		
		CartPage cartpage =  homepage.clickOnCartHeader();
		// Verify that the cart page is displayed by checking the number of items in the cart
		System.out.println("Number of items in cart: " + cartpage.getCartList().size());
		//Printing the cart items
		cartpage.getCartList().forEach(cartItem -> {
			System.out.println("Cart Item: " + cartItem.getText());
		});
		
		// Verify that the cart contains the added products
		Assert.assertTrue(cartpage.verifyProductInCart("Sauce Labs Backpack"),
				"Product 'Sauce Labs Backpack' is not present in the cart");
		Assert.assertTrue(cartpage.verifyProductInCart("Sauce Labs Bike Light"),
				"Product 'Sauce Labs Bike Light' is not present in the cart");
		
		// Remove a product from the cart
		cartpage.removeProductFromCart("Sauce Labs Backpack");
		Assert.assertFalse(cartpage.verifyProductInCart("Sauce Labs Backpack"),
				"Product 'Sauce Labs Backpack' was not removed from the cart");
		
		//total price of the cart
		System.out.println("Total price of products in cart: $" + cartpage.getProductPrices());
		
		// Click on the continue shopping button
		//cartpage.clickContinueShopping();
		
		// Click on the checkout button
		cartpage.clickCheckout();
	}
	
}
