package Framework.Design.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Desgin.Abstract.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// Initialize PageFactory elements if needed
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(css = ".inventory_item_price")
	private List<WebElement> productPrices;
	
	public double getProductPrices() {
		return productPrices.stream()
				.mapToDouble(price -> Double.parseDouble(price.getText().replace("$", "")))
				.sum();
	}
	
	@FindBy(css = ".inventory_item_name")
	private List<WebElement> cartList;
	
	public List<WebElement> getCartList() {
		return cartList;
	}
	
	public boolean verifyProductInCart(String productName) {
		return cartList.stream()
				.anyMatch(item -> item.getText().equalsIgnoreCase(productName));
	}
	
	//Method to remove a product from the cart
	public void removeProductFromCart(String productName) {
		WebElement removeButton = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button"));
		removeButton.click();
		waitForWebElementToDisappear(removeButton);
	}
	
	@FindBy(css = "#continue-shopping")
	private WebElement continueShoppingButton;
	
	public void clickContinueShopping() {
		continueShoppingButton.click();
	}
	
	@FindBy(css = "#checkout")
	private WebElement checkoutButton;	
	
	public Checkoutpage1 clickCheckout() {
		checkoutButton.click();
		return new Checkoutpage1(driver);
	}
}
