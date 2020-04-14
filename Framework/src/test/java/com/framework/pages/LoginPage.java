package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	@FindBy(id="user-name") WebElement Uname;
	@FindBy(id="password") WebElement Pass;
	@FindBy(xpath="//input[@value='LOGIN']") WebElement LoginBtn;

	public void loginToApp(String username,String password) {
		Uname.sendKeys(username);
		Pass.sendKeys(password);
		LoginBtn.click();
	}
}