package hackathon;


	
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtentReport;

public class TopCities{
	public static ExtentReports report=ExtentReport.getReportInstance();
	public static ExtentTest logger;

	public static void printCities(WebDriver driver) throws InterruptedException, IOException{
		
		List<WebElement> Top_Cities=driver.findElements(By.className("item"));
		logger = report.createTest("Top Cities");
		logger.log(Status.PASS, "clicked diagnostics");
		Thread.sleep(2000);
		
		try {
			System.out.println("===============================================");
			System.out.println("Top cities: ");
			System.out.println("===============================================");

	        DriverSetup.takeScreenshot("TopCities");
			for (WebElement cities : Top_Cities) {
				System.out.println(cities.getText());
			}
			
			Thread.sleep(2000);
		
			logger.log(Status.PASS, "list of top cities printed");
		}catch(Exception e) {
			logger.log(Status.FAIL, "failed");
	        DriverSetup.takeScreenshot("TopCitiesFailed");
		}
		
		Thread.sleep(2000);
		driver.quit();
	}

}
