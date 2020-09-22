package com.qa.rcs.traxx.TestScripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.qa.rcs.traxx.AutomationBase.TestBase;
import com.qa.rcs.traxx.WebPages.LoginPage;

public class LoginTS extends TestBase {
	
	LoginPage lp; 
	
	public LoginTS()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	@Test
	public void loginTestCase()
	{
		//lp=new LoginPage();
		//lp.login();
	}

}
