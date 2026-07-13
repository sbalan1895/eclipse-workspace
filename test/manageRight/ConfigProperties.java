package manageRight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
	private static Properties prop = new Properties();
	
	public static void loadProperties() throws IOException {
		try
		{
			prop.load(new FileInputStream("configdatafile.properties"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
