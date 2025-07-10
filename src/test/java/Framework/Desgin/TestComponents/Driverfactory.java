package Framework.Desgin.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Resources.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driverfactory {

	public WebDriver driver;
	
	public WebDriver getDriver() throws IOException {
	
		String browserName =  ConfigReader.get("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			
			if(browserName.contains("headless")) 
			{
				// Initialize Chrome driver in headless mode
				
				options.addArguments("headless");
				driver = new ChromeDriver(options);
			} 
			else 
			{
				// Initialize Chrome driver in normal mode
				options.addArguments("--incognito");
				options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
				    put("credentials_enable_service", false);
				    put("profile.password_manager_enabled", false);
				}});
				driver = new ChromeDriver(options);
			}
		} 
		else if(browserName.equalsIgnoreCase("firefox")) 
		{
			// Initialize Firefox driver
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		else if(browserName.equalsIgnoreCase("edge")) 
		{
			// Initialize Edge driver
		    WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize(); // Maximize the browser window
		
		return driver;
	}
}
