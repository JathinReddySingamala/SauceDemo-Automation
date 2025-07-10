package Framework.Design.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Desgin.Abstract.AbstractComponents;

public class Homepage extends AbstractComponents {

	WebDriver driver;
	
	public Homepage(WebDriver driver) 
	{
		super(driver); 
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".inventory_item")
	private List<WebElement> products;
	
	private By productsBy = By.cssSelector(".inventory_container");
	private By addToCart = By.cssSelector(".pricebar button");
	
	public boolean isHomepageDisplayed() {
	   
	    return waitForElementToAppear(productsBy).isDisplayed();
	}

	
	public List<WebElement> getProducts() {
	   	waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		return getProducts().stream()
				.filter(product -> product.findElement(By.cssSelector(".inventory_item_name")).getText().equalsIgnoreCase(productName))
				.findFirst()
				.orElse(null);
	}
	
	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		if (product != null) {
			product.findElement(addToCart).click();
		
		} else {
			System.out.println("Product not found: " + productName);
		}
	}
	
	public void removeProductFromCart(String productName) {
		WebElement product = getProductByName(productName);
		if (product.findElement(addToCart).getText().equalsIgnoreCase("Remove") ) {
			product.findElement(addToCart).click();
		} else {
			System.out.println("Product is not added to cart " + productName);
		}
		
	}
	
	@FindBy(css = "#react-burger-menu-btn")
	private WebElement burgerMenu;
	@FindBy(xpath = "//nav/a")
	private List<WebElement> menuItems;
	
	@FindBy(css = ".bm-menu-wrap")
	private WebElement menuWrap;//this is used to check if the menu is open

	public void clickOnBurgerMenu() {
		burgerMenu.click();
		
	}
	
	//method to get the text of the menu items
	public List<String> getMenuItemsText() {
		clickOnBurgerMenu();
		waitForAttributeToChange(menuWrap, "aria-hidden", "false");
		return menuItems.stream()
				.map(WebElement::getText)
				.toList();
	}
	
	//method to click on a menu item by its text
	public void clickMenuItemByText(String Text) {
	    clickOnBurgerMenu();
	    waitForAttributeToChange(menuWrap, "aria-hidden", "false");

	    menuItems.stream()
	        .filter(e -> e.getText().equalsIgnoreCase(Text))
	        .findFirst()
	        .orElseThrow(() -> new RuntimeException(Text + " not found in menu"))
	        .click();
	}
	
}
