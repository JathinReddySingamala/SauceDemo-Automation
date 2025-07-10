package Framework.Design.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Desgin.Abstract.AbstractComponents;

public class Checkoutpage1  extends AbstractComponents {

	WebDriver driver;
	
	public Checkoutpage1(WebDriver driver)
	{
	    super(driver);
		this.driver = driver;
		// Initialize PageFactory elements if needed
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#first-name")
	private WebElement firstNameInput;
	@FindBy(css = "#last-name")
	private WebElement lastNameInput;
	@FindBy(css = "#postal-code")
	private WebElement postalCodeInput;
	
	// Method to fill in the checkout form
	public void fillCheckoutForm(String firstName, String lastName, String postalCode) 
	{
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		postalCodeInput.sendKeys(postalCode);
	}
	
	
	@FindBy(css = "#continue")
	private WebElement continueButton;
	// Method to click the continue button
	public Checkoutpage2 clickContinue() 
	{
		continueButton.click();
		return new Checkoutpage2(driver);
	}
}
