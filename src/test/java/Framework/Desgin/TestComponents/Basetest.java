package Framework.Desgin.TestComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.Design.PageObjects.CartPage;
import Framework.Design.PageObjects.Checkoutpage1;
import Framework.Design.PageObjects.Checkoutpage2;
import Framework.Design.PageObjects.Checkoutpage3;
import Framework.Design.PageObjects.Homepage;
import Framework.Design.PageObjects.LoginPage;

public class Basetest extends Driverfactory {

	public WebDriver driver;
	public LoginPage loginPage;
	public Homepage homepage;
	public CartPage cartPage;
	public Checkoutpage1 checkoutPage1;
	public Checkoutpage2 checkoutPage2;
	public Checkoutpage3 checkoutPage3;
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage goTo() throws IOException
	{
		driver = getDriver();
		driver.get("https://www.saucedemo.com/");
		loginPage = new LoginPage(driver);
		return loginPage;
		
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeApplication()
	{
		driver.quit();
	}
	
	// This method reads JSON data from a file and returns it as a list of HashMaps
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException
	{
		//read json to string
		String JsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		// This method reads JSON data from a file and returns it as a list of HashMaps
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		return data;
	}
	
	// This method captures a screenshot of the current state of the WebDriver and saves it to a specified file path
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName + "_" + timeStamp + ".png");
		FileUtils.copyFile(source, file);
		return file.getAbsolutePath();
	}
}
