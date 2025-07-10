package Framework.Desgin.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.Design.PageObjects.CartPage;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponents(WebDriver driver) 
	{
		// Initialize the PageFactory elements for this component
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "#shopping_cart_container")
	WebElement cartHeader;
	// Method to click on the cart header to navigate to the cart page
	public CartPage clickOnCartHeader() 
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	@FindBy(css = ".shopping_cart_badge")
	 WebElement cartBadge;
	
	public String Added_to_Cart() 
	{
		// Click on the cart header to navigate to the cart page
		return cartBadge.getText();
	}
	
	// Method to wait for a web element to appear 
	public void waitForWebElementToAppear(WebElement element) {
		// Implementation for waiting for a web element to appear
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// Method to wait for a web element to disappear 
	public void waitForWebElementToDisappear(WebElement element) {
		// Implementation for waiting for a web element to disappear
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	// Method to wait for an element to appear using a By locator
	public  WebElement waitForElementToAppear(By findBy) {
		// Implementation for waiting for an element to appear using a By locator
		return wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	// Method to wait for an element to disappear using a By locator
	public void waitForElementToDisappear(By findBy) {
		// Implementation for waiting for an element to disappear using a By locator
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	// Method to wait for an attribute to change
	public void waitForAttributeToChange(WebElement element, String attribute, String value) {
		// Implementation for waiting for an attribute to change
		wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
	}
}
