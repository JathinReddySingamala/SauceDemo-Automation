package Framework.Desgin.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.Desgin.TestComponents.Basetest;
import Framework.Design.PageObjects.Homepage;

public class HomepageTest extends Basetest {

	@Test(groups = {"smoke"})
	public void testHomepageElements() {
		
		Homepage homepage =  loginPage.Sign_in("standard_user", "secret_sauce");
		// Verify that the homepage is displayed by checking the number of products
		System.out.println("Number of products on homepage: " + homepage.getProducts().size());
		
		// Add a product to the cart
	    homepage.addProductToCart("Sauce Labs Backpack");
		Assert.assertEquals(homepage.Added_to_Cart(),"1", "Product was not added to cart successfully");
		homepage.addProductToCart("Sauce Labs Bike Light");
		Assert.assertTrue(homepage.Added_to_Cart().equalsIgnoreCase("2"), "Product was not added to cart successfully");
		//Remove the product from the cart
		homepage.removeProductFromCart("Sauce Labs Backpack");
		
		
	}
	
	@Test(groups = {"positive tests"})
	public void logoutTest() {
		
		Homepage homepage =  loginPage.Sign_in("standard_user", "secret_sauce");
		homepage.clickMenuItemByText("Logout");
		// Verify that the user is logged out by checking the URL
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/", "User is not logged out successfully");
	}
}
