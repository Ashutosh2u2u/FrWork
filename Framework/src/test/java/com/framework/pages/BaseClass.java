package com.framework.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.utility.BrowserFactory;
import com.framework.utility.ConfigDataProvider;
import com.framework.utility.ExcelDataProvider;
import com.framework.utility.Helper;

public class BaseClass {
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setupSuite()
	{ 
		Reporter.log("Setting up reports and Test getting ready", true);
		excel=new ExcelDataProvider();
		config=new ConfigDataProvider();
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"./Reports/EcomDemo_"+Helper.getCurrentDataTime()+".html"));
		report= new ExtentReports();
		report.attachReporter(extent);
		Reporter.log("Setting done--Test can be started", true);
	}
	@Parameters({"browser","urlToBeTested"})
	@BeforeClass
	public void setup(String browser,String urlToBeTested)
	{
		Reporter.log("Trying to start browser -Getting application ready", true);
		//driver=BrowserFactory.startApplication(driver,config.getBrowser(),config.getStagingURL());
		driver=BrowserFactory.startApplication(driver,browser,urlToBeTested);
		Reporter.log("Browser and application is up and Running", true);
	}
	@AfterClass
	public void tearDown()
	{
		BrowserFactory.quiteDriver(driver);
	}
	@AfterMethod
	public void tearDownMethods(ITestResult result) throws IOException 
	{	
		Reporter.log("Test is about to end", true);
		if(result.getStatus()==ITestResult.FAILURE)
		{
			//Helper.captureScreenshots(driver);
			logger.fail("Test Failed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshots(driver)).build());
		}
		/*else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.fail("Test passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshots(driver)).build());
		}

		if(result.getStatus()==ITestResult.SKIP);
		{
			logger.skip("Test Skipped",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshots(driver)).build());			
		}*/
		report.flush();
		Reporter.log("Test completed >>>Reports generated", true);
	}

}
