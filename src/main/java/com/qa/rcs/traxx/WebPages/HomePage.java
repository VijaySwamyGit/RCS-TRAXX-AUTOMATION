package com.qa.rcs.traxx.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.rcs.traxx.AutomationBase.TestBase;
import com.qa.rcs.traxx.AutomationUtils.TestUtil;

public class HomePage extends TestBase{
	
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement logoutLink;
	
	
	//Left Side Navigational Links(LSNL) STARTS
	@FindBy(xpath="//a[contains(text(),'Home')]")
	WebElement lsnlHomeLink;
	
	//PROCUREMENT SUB MODULE STARTS
	@FindBy(xpath="//a[contains(text(),'Procurement')]")
	WebElement lsnlProcurementLink;
	
	
	
	@FindBy(xpath="//a[contains(text(),'Purchase Request')]")
	WebElement lsnlpurchaseRequestLink;
	@FindBy(xpath="//li[@class='sub_menu']//a[contains(text(),'Request')]//span[@class='fa fa-chevron-down']")
	WebElement lsnlRequestLink;
	@FindBy(xpath="//a[contains(text(),'Create PR')]")
	WebElement lsnlCreatePRLink;
	
	//PROCUREMENT SUB MODULE ENDS
	
	@FindBy(xpath="//a[contains(text(),'Inwarding')]//i[@class='fa fa-edit']")
	WebElement lsnlInwardingLink;
	
	@FindBy(xpath="//a[contains(text(),'Asset Movements')]")
	WebElement lsnlAssetMovementsLink;
	//Left Side Navigational Links(LSNL) ENDS
	
	
	public void navigateToCreatePRPage()
	{
		TestUtil.delayToSync();
		lsnlProcurementLink.click();
		TestUtil.delayToSync();
		lsnlpurchaseRequestLink.click();
		
		TestUtil.delayToSync();
		lsnlRequestLink.click();
		TestUtil.delayToSync();
		lsnlCreatePRLink.click();
	}
	

}
