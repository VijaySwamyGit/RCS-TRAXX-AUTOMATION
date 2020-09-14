package com.qa.rcs.traxx.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.rcs.traxx.AutomationBase.TestBase;

public class CreatePR_Page extends TestBase{
	
	public CreatePR_Page()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@value='Direct PR']")
	WebElement  directPRButton;
	
	
	public void createDirectPR()
	{
		directPRButton.click();
	}

}
