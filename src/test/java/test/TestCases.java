package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import hackathon.DriverSetup;
import hackathon.Practo_HomePage;
import hackathon.TopCities;
import utilities.Config;
import utilities.ExtentReport;

public class TestCases {
	private static WebDriver driver;
	private static int choice;
	public ExtentReports report = ExtentReport.getReportInstance();
	public ExtentTest logger;

	@BeforeMethod
	public void setUp() {
		// Set up the WebDriver
		choice = DriverSetup.ch = 1;
		driver = DriverSetup.getDriver();
	}

	@AfterMethod
	public void tearDown() {
		// Close the WebDriver after each test
		driver.quit();
	}

	@Test(priority = 1)
	public void testDisplayTopCities() {
		// This will display all the Top-cities
		try {
			driver.get(Config.property().getProperty("baseURL") + "india");
			TopCities.printCities(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void testHospitalsWithoutParking() {
		System.out.println("=================================================================");
		System.out.println("Hospital Names which have [3.5+ ratings,Open24hr] but Without Parking: ");
		System.out.println("=================================================================");
		// Displays Hospitals with Rating > 3.5 stars and Operate 24/7.
		try {
			driver.get(Config.property().getProperty("baseURL"));
			Practo_HomePage.hospital(driver, Config.property().getProperty("city"),
					Config.property().getProperty("search"), 3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*@Test(priority = 4)
	public void testHospitalsWithParking() {
		// Example: Choose option 4 and assert the hospitals with parking, rating > 3.5,
		// operate 24/7
		System.out.println("===================================================================");
		System.out.println("Hospital Names Which have Parking Facility and [3.5+ ratings,Open24hr]: ");
		System.out.println("===================================================================");
		try {
			driver.get(Config.property().getProperty("baseURL"));
			Practo_HomePage.hospital(driver, Config.property().getProperty("city"),
					Config.property().getProperty("search"), 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/

	@Test(priority = 2)
	public void testDisplayHospitalsWithoutParking() {
		// Displays Hospitals only with Rating > 3.5 stars and Operate 24/7 but without
		// parking.
		System.out.println("=====================================================");
		System.out.println("Hospital Names Only Which have [3.5+ ratings,Open24hr]: ");
		System.out.println("=====================================================");

		try {
			driver.get(Config.property().getProperty("baseURL"));
			Practo_HomePage.hospital(driver, Config.property().getProperty("city"),
					Config.property().getProperty("search"), 4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
