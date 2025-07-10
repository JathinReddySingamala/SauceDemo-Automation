package Framework.Design.Report;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + File.separator+ "report"+File.separator+"index.html";
		 System.out.println("Extent Report will be written to: " + path); 
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Saucedemo Website Automation Results");
		reporter.config().setDocumentTitle("Test results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Jathin");
		
		return extent;
	}
}
