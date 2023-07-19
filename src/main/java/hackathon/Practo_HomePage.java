package hackathon;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.ExtentReport;



public class Practo_HomePage {
	public static ExtentReports report=ExtentReport.getReportInstance();
	public static ExtentTest logger;

	public static void hospital(WebDriver driver,String City,String Search,int choice) throws InterruptedException
	{
		String h=driver.getTitle();
		
		System.out.println(h);
		DriverSetup.takeScreenshot("Hospital_HomePage/");
		
		WebElement city=driver.findElement(By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/input"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		logger = report.createTest("City as Bangalore");
		city.click();
		city.clear();
		city.sendKeys(City);
		logger.log(Status.INFO,"City Textbox was Cleared and City was sent as Bangalore");
		
		logger = report.createTest("City Dropdown should be visible");
		Thread.sleep(1000);
		List<WebElement> city_list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("c-omni-suggestion-item__content__title")));
		logger.log(Status.INFO,"City Dropdown was visible");
		for(int i=0;i<city_list.size();i++)
		{
			if(city_list.get(i).getText().equalsIgnoreCase("Bangalore"))
			{
				city_list.get(i).click();
				logger.log(Status.INFO,"City as Banglore was clicked");
				break;
			}
		}
		DriverSetup.takeScreenshot("Hospital_HomePage/City_Entered_Bangalore");
		
		logger = report.createTest("Search as Hospitals");
		WebElement data=driver.findElement(By.xpath("//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input"));
		data.click();
		data.sendKeys(Search);
		logger.log(Status.INFO,"Search Textbox was Cleared and Hospitals was sent");
		
		logger = report.createTest("Search Dropdown should be visible for [Hospital]");
		Thread.sleep(3000);
		List<WebElement> list=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("c-omni-suggestion-item__content__title")));
		logger.log(Status.INFO,"Search Dropdown was visible");
		
		DriverSetup.takeScreenshot("Hospital_HomePage/Hospital_Entered_SearchBox");
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getText().equalsIgnoreCase("Hospital"))
			{
				list.get(i).click();
				logger.log(Status.INFO,"Search as [Hospitals] is  clicked");
				break;
			}
		}
		
		Thread.sleep(3000);
		//DriverSetup.takeScreenshot("Hospital_Data_Page/");
		if(choice!=0)
		{
			Hospital_data.hospital_data_list(driver,choice);
		}
		driver.quit();
		report.flush();
		
	}
	
	
}
