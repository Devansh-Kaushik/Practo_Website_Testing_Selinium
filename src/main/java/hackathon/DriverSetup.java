package hackathon;


import java.io.File;

import java.io.IOException;

//import org.apache.commons.c.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/* IMPORTANT:- DriverSetup --getDriver()
-------------------------------------------------
PLEASE DO NOT MAKE ANY CHANGES OR MOFICATIONS IN THIS PROGRAM */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.DateUtils;
import utilities.ExtentReport;


public class DriverSetup
{ 
    private static WebDriver driver;
    public static int ch;
    public ExtentReports report=ExtentReport.getReportInstance();
	public static ExtentTest logger;
    
    
    public static WebDriver getDriver()
	{
		
    	if(ch==1)
    	{
    		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
    		ChromeOptions options = new ChromeOptions();
    		options.addArguments("--start-maximized");
    		options.addArguments("--disable-extensions");
    		options.setAcceptInsecureCerts(true);
    		driver = new ChromeDriver(options);
		    
    	}
    	else if(ch==2)
    	{
    		System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
    		EdgeOptions options = new EdgeOptions();
    		options.addArguments("--start-maximized");
    		options.addArguments("--disable-infobars");
    		options.setCapability("ms:edgeOptions", "{\"excludeSwitches\": [\"enable-automation\"]}");
    		options.setCapability("pageLoadStrategy", "eager");
    		options.setCapability("acceptInsecureCerts", true);
    		driver = new EdgeDriver(options);

		    
		    
    	}
    	else if(ch==3)
    	{
    		//System.setProperty("webdriver.firefox.driver", "Drivers\\chromedriver.exe");
    		driver=(WebDriver) new FirefoxDriver();
    		
		    
    	}
		return driver;
	}
    
    public static void takeScreenshot(String name) {
        File destFile = new File("Screenshots/" + name + DateUtils.getTimeStamp() + ".png");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
			FileHandler.copy(screenshot, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (logger != null) {
            try {
				logger.addScreenCaptureFromPath(destFile.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }



}

	 	  	  		 	     	     	      	 	
