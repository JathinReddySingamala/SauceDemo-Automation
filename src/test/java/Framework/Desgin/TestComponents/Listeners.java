package Framework.Desgin.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.Design.Report.ExtentManager;
import Framework.Design.Report.ExtentReporterNG;

public class Listeners extends Basetest implements ITestListener{

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getExtentReports().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test Passed");
    }


    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());

        try 
        {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            String filepath = getScreenShot(result.getMethod().getMethodName(), driver);
            ExtentManager.getTest().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
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
        ExtentManager.getExtentReports().flush();
    }

	
	
}
