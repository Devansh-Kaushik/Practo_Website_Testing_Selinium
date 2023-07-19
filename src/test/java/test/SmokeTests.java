package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hackathon.DriverSetup;
import hackathon.Driver_Choice;
import hackathon.Practo_HomePage;
import utilities.Config;

public class SmokeTests {
	
	
	private static WebDriver driver;
	private static int choice;
	
	@BeforeTest
	public void SmokeTest() {
		choice=DriverSetup.ch=1;
        driver = DriverSetup.getDriver();
    }
	
	
	@Test
    public void testHospitalsHomePage(){
        //This will test the Practo_HomePage
       try {
    	   driver.get(Config.property().getProperty("baseURL"));
    	   Practo_HomePage.hospital(driver,Config.property().getProperty("city"),Config.property().getProperty("search"),0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    }
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
}