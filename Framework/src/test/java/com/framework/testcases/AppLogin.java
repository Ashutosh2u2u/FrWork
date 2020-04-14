package com.framework.testcases;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.framework.pages.BaseClass;
import com.framework.pages.LoginPage;



public class AppLogin extends BaseClass {	

	@Test
	public void LoginToApp()
	{	logger=report.createTest("login to ecomDemo");	
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting the application");
		loginPage.loginToApp(excel.getStringData("Login", 0, 0),excel.getStringData("Login", 0, 1));
		logger.pass("Login done");
		
	}

}
