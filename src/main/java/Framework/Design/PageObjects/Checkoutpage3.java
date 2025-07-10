package Framework.Design.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Desgin.Abstract.AbstractComponents;

public class Checkoutpage3 extends AbstractComponents {
	
	WebDriver driver;
	
	public Checkoutpage3(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		// Initialize PageFactory elements if needed
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".complete-header")
	private WebElement confrimationMessage;
	
	// Method to get the confirmation message after checkout
	public String getConfirmationMessage() 
	{
		return confrimationMessage.getText();
	}
	
	@FindBy(css = "#back-to-products")
	private WebElement backToProductsButton;
	
	// Method to click the "Back to Products" button
	public void clickBackToProducts() 
	{
		backToProductsButton.click();
		waitForWebElementToDisappear(backToProductsButton);
	}
}
