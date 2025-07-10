package Framework.Design.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Desgin.Abstract.AbstractComponents;

public class Checkoutpage2 extends AbstractComponents {

	WebDriver driver;
	
	public Checkoutpage2(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Initialize PageFactory elements if needed
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(css = ".title")
	private WebElement checkoutPage2_Title;
	// Check if the checkout page 2 is displayed
	public String isCheckoutPage2_Displayed() {
		return checkoutPage2_Title.getText();
	}
	
	@FindBy(css = ".summary_total_label")
	private WebElement totalPriceLabel;
	
	// Method to get the total price from the summary
	public String getTotalPrice() {
		return totalPriceLabel.getText();
	}
	
	@FindBy(css = "#finish")
	private WebElement finishButton;
	
	// Method to click the finish button
	public Checkoutpage3 clickFinish() {
		finishButton.click();
		return new Checkoutpage3(driver);
	}
}
