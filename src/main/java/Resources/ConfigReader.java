package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	static Properties prop;
	
	static 
	{
		try 
		{
			prop = new Properties();
		    FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main"
					+ "//java//Resources//config.properties");
			prop.load(fis);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static String get(String key) 
	{
		String value = System.getProperty(key);
		if(value != null)
		{
			return value;
		}
		
		return prop.getProperty(key);
	}
}
