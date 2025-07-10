package Framework.Desgin.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import Resources.ConfigReader;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry;

	public Retry() 
	{
		maxTry = Integer.parseInt(ConfigReader.get("retryCount"));
	}
	
	@Override
	public boolean retry(ITestResult result) {
		if (count < maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
