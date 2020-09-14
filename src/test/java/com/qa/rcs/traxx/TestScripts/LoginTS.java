package com.qa.rcs.traxx.TestScripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.qa.rcs.traxx.AutomationBase.TestBase;
import com.qa.rcs.traxx.WebPages.LoginPage;

public class LoginTS extends TestBase {
	
	LoginPage lp; 
	
	//CONSTRUCTOR STARTS ADDED from Git Repo
	public LoginTS()
	{
		PageFactory.initElements(driver, this);
	}
	//CONSTRUCTOR STARTS ADDED from Git Repo
	
	
	@Test
	public void loginTestCase()
	{
		lp=new LoginPage();
		lp.login();
	}

}
