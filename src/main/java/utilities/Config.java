package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static Properties property()
	{
		FileReader reader = null;
		try {
			reader = new FileReader("src//main//resources//config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    Properties p=new Properties();
	    try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return p;
	}
	

}
