package Framework.Desgin.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.Design.Report.ExtentReporterNG;

public class Listeners extends Basetest implements ITestListener{

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
	
		
		test = extent.createTest(result.getMethod().getMethodName());
		extendTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extendTest.get().log(Status.PASS, "test passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    extendTest.get().fail(result.getThrowable());

	    try {
	        driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    String filepath = null;
	    try {
	        filepath = getScreenShot(result.getMethod().getMethodName(), driver);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    extendTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	
	
}
