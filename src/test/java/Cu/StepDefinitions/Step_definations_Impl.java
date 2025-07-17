package Cu.StepDefinitions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Framework.Desgin.TestComponents.Basetest;
import Framework.Design.PageObjects.Homepage;
import Framework.Design.PageObjects.LoginPage;
import Framework.Design.Report.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Step_definations_Impl extends Basetest {

	//The Below for Login Page
	
//	@Given("the user is on the login page")
//	public void the_user_is_on_the_login_page() throws IOException {
//	    
//		loginPage = goTo();
//	}
//	
	@Before
	 public void beforeScenario(Scenario scenario) throws Exception {
	        loginPage = goTo(); 
	        
	        ExtentManager.setTest(ExtentManager.getExtentReports().createTest(scenario.getName()));
	        
	        System.out.println("Browser launched and login completed");
	    }
	
	@After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
        	String path = getScreenShot(scenario.getName(), driver);
        	scenario.attach(Files.readAllBytes(Paths.get(path)), "image/png", "Failure Screenshot");
        	
        	ExtentManager.getTest().log(Status.FAIL, "Scenario Failed").addScreenCaptureFromPath(path);

        }
        else {
            ExtentManager.getTest().log(Status.PASS, "Scenario Passed");
        }
        //ExtentManager.flushReports();
        closeApplication();
        System.out.println("Browser closed after scenario");
    }
	

	@When("the user logs in with username {string} and password {string}")
	public void the_user_logs_in_with_username_and_password(String username, String password) {
		System.out.println("LoginPage in Step Def: " + loginPage);
    homepage = loginPage.Sign_in(username, password);
    }
	
    @Then ("the homepage should be displayed")
    public void the_homepage_should_be_displayed() {
    	 Assert.assertTrue(homepage.isHomepageDisplayed(), "Homepage is not displayed after login");
	}
    
    @Then ("the error message should be {string}")
    public void the_error_message_should_be(String errorMessage) {
		String actualErrorMessage = loginPage.getErrorMessage();
		Assert.assertEquals(actualErrorMessage, errorMessage, "Error message does not match expected");
	}
    
    
    //The Below is for Home Page

    @Then("the product list should be visible")
    public void productListShouldBeVisible() {
        int productCount = homepage.getProducts().size();
        Assert.assertTrue(productCount > 0, "No products visible on homepage");
    }

    @When("the user adds {string} to the cart")
    public void userAddsProductToCart(String productName) {
        homepage.addProductToCart(productName);
    }

    @When("the user removes {string} from the cart")
    public void userRemovesProductFromCart(String productName) {
        homepage.removeProductFromCart(productName);
    }

    @Then("the cart badge should show {string}")
    public void cartBadgeShouldShow(String expectedCount) {
        String cartCount = homepage.Added_to_Cart();
        Assert.assertEquals(cartCount, expectedCount);
    }

    @When("the user clicks on the menu item {string}")
    public void userClicksOnMenuItem(String itemName) {
        homepage.clickMenuItemByText(itemName);
    }

    @Then("the user should be redirected to the login page")
    public void userShouldBeRedirectedToLoginPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/");
    }

    
    //The Below is for Cart Page
    
    @When("the user navigates to the cart page")
    public void userNavigatesToCart() {
        cartPage = homepage.clickOnCartHeader();
    }

    @Then("the cart should contain {string}")
    public void cartShouldContain(String productName) {
        Assert.assertTrue(cartPage.verifyProductInCart(productName), "Expected product not found in cart: " + productName);
    }

    @Then("the cart should not contain {string}")
    public void cartShouldNotContain(String productName) {
        Assert.assertFalse(cartPage.verifyProductInCart(productName), "Unexpected product found in cart: " + productName);
    }

    @When("the user removes {string} from the cartlist")
    public void userRemovesProduct(String productName) {
        cartPage.removeProductFromCart(productName);
    }

    @Then("the total cart price should be {string}")
    public void verifyTotalCartPrice(String expectedPrice) {
        double actualPrice = cartPage.getProductPrices();
        String formattedPrice = String.format("$%.2f", actualPrice);
        Assert.assertEquals(formattedPrice, expectedPrice);
    }

    @When("the user clicks on checkout")
    public void userClicksCheckout() {
    	checkoutPage1 =cartPage.clickCheckout();
    }

    @Then("the checkout page should be displayed")
    public void checkoutPageDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"));
    }
    
    // The Below is for Checkout Page
    
    @When("the user enters first name {string}, last name {string}, and postal code {string}")
    public void userEntersCheckoutInfo(String fname, String lname, String zip) {
        checkoutPage1.fillCheckoutForm(fname, lname, zip);
    }

    @When("the user clicks on continue")
    public void userClicksOnContinue() {
        checkoutPage2 = checkoutPage1.clickContinue();
    }

    @Then("the checkout overview page should be displayed")
    public void checkoutOverviewDisplayed() {
        String actualTitle = checkoutPage2.isCheckoutPage2_Displayed();
        Assert.assertEquals(actualTitle, "Checkout: Overview");
    }

    @Then("the total price should be visible")
    public void totalPriceShouldBeVisible() {
        System.out.println("Total price: " + checkoutPage2.getTotalPrice());
        Assert.assertTrue(checkoutPage2.getTotalPrice().startsWith("Total"));
    }

    @When("the user clicks on finish")
    public void userClicksOnFinish() {
        checkoutPage3 = checkoutPage2.clickFinish();
    }

    @Then("the confirmation message {string} should be displayed")
    public void confirmationMessageShouldBeDisplayed(String expectedMessage) {
        String message = checkoutPage3.getConfirmationMessage();
        
        Assert.assertTrue(checkoutPage3.getConfirmationMessage().equals(expectedMessage),
				"Confirmation message is not displayed");
    }

    @Then("the user clicks on back to products")
    public void userClicksOnBackToProducts() {
        checkoutPage3.clickBackToProducts();
    }
    
    
    
    
}
