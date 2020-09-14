package com.qa.rcs.traxx.TestScripts;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.qa.rcs.traxx.AutomationBase.TestBase;
import com.qa.rcs.traxx.WebPages.CreatePR_Page;
import com.qa.rcs.traxx.WebPages.CreatePurChaseRequest_Page;
import com.qa.rcs.traxx.WebPages.HomePage;

public class CreatePurchaseRequestTS extends TestBase {
	
	CreatePurChaseRequest_Page createPurChaseRequestPage;
	CreatePR_Page createPRPage;
	HomePage homePage;
	
	//CONSTRUCTOR STARTS
	public CreatePurchaseRequestTS()
	{
		PageFactory.initElements(driver, this);
	}
	//CONSTRUCTOR ENDS
		
		@Test
		public void createPurchaseRequestTestCase()
		{
			homePage = new HomePage();
			createPRPage = new CreatePR_Page();
			createPurChaseRequestPage = new CreatePurChaseRequest_Page();
			
			homePage.navigateToCreatePRPage();
			createPRPage.createDirectPR();
			createPurChaseRequestPage.CreatePurChaseRequest();
			
		}
	}


