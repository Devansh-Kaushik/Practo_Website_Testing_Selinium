package hackathon;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtentReport;

public class Hospital_data {
	public static String current_page;
	public static String Name;
	public static String ratings;
	public static ExtentReports report=ExtentReport.getReportInstance();
	public static ExtentTest logger;
	private static int time=0;
	
	// Handling multiple tabs
	public static String handle(WebDriver driver) {
		current_page=driver.getWindowHandle();
	    for (String windowHandle : driver.getWindowHandles()) {
	        if (!windowHandle.equals(current_page)) {
	            return windowHandle;
	        }
	    }
	    return null;
	}
	 
	// Checking that if hospital having Parking facility or not and 
	// basis on that displaying the data
	public static void Parking_Check(WebDriver driver)
	{
		String page=driver.getPageSource();
		if(page.contains("Car Parking"))
		{
			System.out.println("Hospital :"+Name);
            System.out.println("Ratings: " +ratings +" and Timings: Open 24/7");
            System.out.println();
            DriverSetup.takeScreenshot("Hospital_With_CarParking_Facility/");
		}
		driver.close();
    	driver.switchTo().window(current_page);
		
	}
	
	//Displaying the data without Parking Facility if Rating>3.5 and Open24/7
	public static void Display_data_Without_Parking()
	{
		System.out.println("Hospital :"+Name);
        System.out.println("Ratings: " +ratings +" and Timings: Open 24/7");
        System.out.println();
        if(time!=5)
        {
        	DriverSetup.takeScreenshot("Hospital_With(3.5+)Ratings_And_Open24hr/");
        }
	}
	
	// Displaying just the hospital name if Rating>3.5 and Open24/7
	public static void Display_HospitalNames_Only()
	{
		
		System.out.println("Hospital :"+Name);	
		if(time!=5)
        {
        	DriverSetup.takeScreenshot("Hospital_Name's_with_Satisfied_Condition/");
        }
	}
	
	// This is just the switch case 
	//This helps to go to the function to execute according to the users requirements
	public static void Switch_cases(WebDriver driver, WebElement name ,int choice) throws InterruptedException
	{
		switch (choice) {
	    case 3:
	        Display_data_Without_Parking();
	        break;
	    case 4:
	        Display_HospitalNames_Only();
	        break;
	    case 5:
	        name.click();
	        Thread.sleep(1000);
	        driver.switchTo().window(handle(driver));
	        Parking_Check(driver);
	        break;
		}
	}
	
	//This is the heart of the entire program
	//This is where it performs all functionality and keeps on doing until the page data end-up
	//This is where all above function are being called
	public static void hospital_data_list(WebDriver driver,int choice) throws InterruptedException
	{
		
		Thread.sleep(2000);
		logger = report.createTest("Feteching Process Should be started");
		
	    WebElement total = driver.findElement(By.className("c-prime-header"));
	    String numberString = total.getText().replaceAll("[^0-9]", "");
	    int number = Integer.parseInt(numberString);
	    int j = 2;
	    int previousSize = 0;
	    logger.log(Status.INFO,"Fetching Hospitals data process started");
	    while (j < number) {
	        List<WebElement> elements = driver.findElements(By.className("c-estb-card"));

	        if (elements.size() > previousSize) {
	            for (int i = previousSize; i < elements.size(); i++) {
	            	WebElement ele = elements.get(i);
	            	Pattern pattern=Pattern.compile("(\\d+(\\.\\d+)?)\\s+\\((\\d+) rated\\)");
	            	Matcher matcher=pattern.matcher(ele.getText());
	            	
	            	ratings = matcher.find() ? matcher.group(1) : "1.0";
	            	
	            	String data = ele.getText();
	            	WebElement name=ele.findElement(By.className("line-1"));
	            	Name=name.getText();
	                if (!Name.equals("Practo Care Surgeries") && (Double.parseDouble(ratings)>3.5) && 
	                		data.contains("MON - SUN 00:00AM - 11:59PM")) {
	                	
	                	Switch_cases(driver, name ,choice);
	                }
	                
  
	            }
	            previousSize = elements.size();
	        }
	        Actions act = new Actions(driver);
	        act.moveToElement(elements.get((int) elements.size()-1)).sendKeys(org.openqa.selenium.Keys.DOWN).perform();
	        j+=8;
	    }
	    logger.log(Status.INFO,"Fetching Hospitals data Finished");
	    
	    report.flush();
	}
	

}
