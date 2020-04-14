package com.framework.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	public static String captureScreenshots(WebDriver driver)
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String ScreenshotPath=System.getProperty("user.dir")+"./Screenshots/"+getCurrentDataTime()+".png";
		try {
			FileHandler.copy(src,new File(ScreenshotPath));
			System.out.println("Screenshot captured");
		} 
		catch (Exception e)
		{
			System.out.println("Unable to capture screenshot" +e.getMessage());
		}
		return ScreenshotPath;
	}
	
	public static String getCurrentDataTime()
	{
		DateFormat customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate=new Date();
		return customFormat.format(currentDate);
	}

}
