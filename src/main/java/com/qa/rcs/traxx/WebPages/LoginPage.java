package com.qa.rcs.traxx.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.qa.rcs.traxx.AutomationBase.TestBase;
import com.qa.rcs.traxx.AutomationUtils.TestUtil;

import junit.framework.Assert;

public class LoginPage extends TestBase {
	
	
	HomePage hp = new HomePage();
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@class='form-top']")
	WebElement rcsTraxxLogoImg;
	
	@FindBy(xpath="//input[@name='username']")
	WebElement userNameTextField;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passwordTextField;
	
	@FindBy(xpath="//button[@class='btn']")
	WebElement loginButton;
	
	@FindBy(xpath="//u[contains(text(),'Forgot Password')]")
	WebElement forgotPasswordLink;
	
	@FindBy(xpath="//a[contains(text(),'Click here for Vendor Registration')]")
	WebElement clickHereForVendorRegistrationLink;
	
	
	public void login(String un, String pwd)
	{
		String HomePageScreen="";
		userNameTextField.sendKeys(un);
		passwordTextField.sendKeys(pwd);
		TestUtil.delayToSync();
		loginButton.click();
		
		String loginValidationMsg = hp.logoutLink.getText();
		Assert.assertEquals(loginValidationMsg, "Logout");
		TestUtil.takeScreenshot(HomePageScreen);
		Reporter.log("Login TestCase Pass---",true);
		
	}
	
	

	
	
}
