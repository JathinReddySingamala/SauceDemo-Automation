package Framework.Design.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Desgin.Abstract.AbstractComponents;

public class LoginPage extends AbstractComponents {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) 
	{
		// Call the constructor of the parent class (if any) to initialize common components
		 super(driver);
		// Initialize the PageFactory elements for this page
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "user-name")
	private WebElement username;
	
	@FindBy(id = "password")
	private WebElement password;
	
	@FindBy(id = "login-button")
	private WebElement login;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement errorMessage;
	
	public Homepage Sign_in(String username, String password) {
		
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		login.click();
		
		Homepage homepage = new Homepage(driver);
		return homepage;
		
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
}
