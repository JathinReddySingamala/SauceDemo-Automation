package Cucumber;

import org.testng.annotations.AfterSuite;

import Framework.Design.Report.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/Cucumber",
				 glue = "Cu.StepDefinitions",
				 monochrome = true,
				 plugin = {"html:target/cucumber.html", 
						   "json:target/cucumber.json",            
					       "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
						  })
public class TestRunner extends AbstractTestNGCucumberTests {

//	@AfterSuite
//	 public void afterSuite() {
//        ExtentManager.flushReports();
//        System.out.println("Extent report flushed in @AfterSuite.");
//    }

}
