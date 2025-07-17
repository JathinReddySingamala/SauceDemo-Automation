package Framework.Design.Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	 private static ExtentReports extent = ExtentReporterNG.getReportObject(); // only one instance
	    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	    public static ExtentReports getExtentReports() {
	        return extent;
	    }

	    public static void setTest(ExtentTest test) {
	        extentTest.set(test);
	    }

	    public static ExtentTest getTest() {
	        return extentTest.get();
	    }
	    
	    public static void flushReports() {
	        extent.flush();
	    }
	    
	    static {
	        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	            ExtentManager.getExtentReports().flush();
	            System.out.println("Extent report flushed on JVM shutdown.");
	        }));
	    }
}
