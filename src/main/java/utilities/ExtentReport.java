 package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReport {
	public static ExtentReports report;

	public static ExtentReports getReportInstance() {

		if (report == null) {
			String reportName = DateUtils.getTimeStamp() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "/reports/" + reportName); //  /reports/
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 11");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Browser", "Chrome");

			htmlReporter.config().setDocumentTitle("Automation Results");
			htmlReporter.config().setReportName("Finding Hospitals Test Report");
			
			//chartloction
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			//htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
			//htmlReporter.config().setTheme(null)
		}

		return report;
	}
}


