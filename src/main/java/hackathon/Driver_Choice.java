package hackathon;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Config;
import utilities.ExtentReport;

import java.io.*;
import java.util.*;

public class Driver_Choice{
	private static WebDriver Driver;
	public static ExtentReports report=ExtentReport.getReportInstance();
	public static String baseUrl;
	public static ExtentTest logger;
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		// This switch case takes the userinput and sends to the hospital_data file
		
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		int choice;

	    
	    // Setting up the BaseURL from config.Properties file 
	    baseUrl=(String)Config.property().getProperty("baseURL");
		
		do {
		    System.out.println("Choose an option:");
		    System.out.println("1. Choose your driver (1. Chrome  2. Edge): ");
		    int ch1 = sc.nextInt();

		    logger = report.createTest("Choosing Driver");
		    DriverSetup.ch = (ch1 == 1) ? 1 : (ch1 == 2) ? 2 : 3;
		    logger.log(Status.INFO, ch1 == 1 ? "Chrome Driver is being chosen" : ch1 == 2 ? "Edge Driver is being chosen" : ch1 == 3 ? "Firefox Driver is being chosen" : "Unknown driver choice");

		    System.out.println("2. Display Practo HomePage");
		    System.out.println("3. Hospitals with Rating > 3.5 stars and Operate 24/7.");
		    System.out.println("4. Display Hospital names with Rating > 3.5 stars, Operate 24/7, and without Parking");
		    System.out.println("5. Hospitals with Parking facility, Rating > 3.5 stars, Operate 24/7.");
		    System.out.println("6. Display All Top Cities");
		    System.out.println("7. Exit");

		    choice = sc.nextInt();
		    String city=(String)Config.property().getProperty("city");
		    String search=(String)Config.property().getProperty("search");
		    Driver = DriverSetup.getDriver();
		    switch (choice) {
		        case 2:
		            Driver.get(baseUrl);
		            Practo_HomePage.hospital(Driver,city,search,0);
		            break;

		        case 3:
		            Driver.get(baseUrl);
		            Practo_HomePage.hospital(Driver,city,search,3);
		            break;

		        case 4:
		            Driver.get(baseUrl);
		            Practo_HomePage.hospital(Driver,city,search,4);
		            break;

		        case 5:
		            Driver.get(baseUrl);
		            Practo_HomePage.hospital(Driver,city,search,5);
		            break;

		        case 6:
		            Driver.get(baseUrl + "india");

		            try {
		                TopCities.printCities(Driver);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }

		            break;

		        case 7:
		        	Driver.quit();
		        	System.out.println("Thank's");
		            break; // Exit the loop
		            

		        default:
		            System.out.println("Invalid option");
		            break;
		    }
		} while (choice != 7);

		

	}

}
