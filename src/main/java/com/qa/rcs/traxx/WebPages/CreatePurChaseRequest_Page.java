package com.qa.rcs.traxx.WebPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.qa.rcs.traxx.AutomationBase.TestBase;
import com.qa.rcs.traxx.AutomationUtils.TestUtil;

public class CreatePurChaseRequest_Page extends TestBase{
	
	
	public CreatePurChaseRequest_Page()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//select[@id='entity']")
	WebElement entityDD;
	@FindBy(xpath="//select[@id='location']")
	WebElement cityDD;
	@FindBy(xpath="//select[@id='sublocation']")
	WebElement siteDD;
	@FindBy(xpath="//select[@id='business_unit']")
	WebElement costCenterDD;
	@FindBy(xpath="//select[@id='req_by']")
	WebElement requestedByDD;
	@FindBy(xpath="//select[@id='cost_center']")
	WebElement projectDD;
	@FindBy(xpath="//select[@id='depertment']")
	WebElement departmentDD;
	@FindBy(xpath="//select[@id='sec_depertment']")
	WebElement secDepartmentDD;
	@FindBy(xpath="//textarea[@id='subject']")
	WebElement subjectTextField;
	@FindBy(xpath="//div[@class='ql-editor ql-blank']")
	WebElement justiForProposalTextField;
	@FindBy(xpath="//textarea[@id='key_benefits']")
	WebElement keyBenefitsTextField;
	@FindBy(xpath="//textarea[@id='remarks_s']")
	WebElement remarksTextField;
	@FindBy(xpath="//select[@id='budget_status']")
	WebElement budjetStatusDD;
	@FindBy(xpath="//select[@id='post_facto']")
	WebElement postFactDD;
	@FindBy(xpath="//input[@id='Go1']")
	WebElement saveAsDraftButton;
	
	
	public void CreatePurChaseRequest()
	{
		String directPRScreen="";
		Select entity = new Select(entityDD);
		entity.selectByIndex(1);
		Select city = new Select(cityDD);
		city.selectByVisibleText("Bangalore");	
		
		TestUtil.delayToSync();
		Select site = new Select(siteDD);
		site.selectByIndex(3);	
		
		Select costCenter = new Select(costCenterDD);
		costCenter.selectByVisibleText("common division");
		Select requestedBy = new Select(requestedByDD);
		requestedBy.selectByVisibleText("Jenish");
		Select project = new Select(projectDD);
		project.selectByVisibleText("Peter Winter");
		Select department = new Select(departmentDD);
		department.selectByVisibleText("Core Team");
		Select secDepartment = new Select(secDepartmentDD);
		secDepartment.selectByVisibleText("Admin");
		subjectTextField.sendKeys("subject");
		justiForProposalTextField.sendKeys("justification for proposal");
		keyBenefitsTextField.sendKeys("key benefits");
		remarksTextField.sendKeys("remarks");
		Select budjetStatus = new Select(budjetStatusDD);
		budjetStatus.selectByVisibleText("Budgeted");	
		Select postFact = new Select(postFactDD);
		postFact.selectByVisibleText("Yes");
		//
		TestUtil.elementToBeClickable(driver, saveAsDraftButton, 40);
		//
		//saveAsDraftButton.click();
		String draftPRSuccessMsg = TestUtil.handleAcceptAlert();
		draftPRSuccessMsg=draftPRSuccessMsg.replaceAll("[0-9]", "");;
		Assert.assertEquals(draftPRSuccessMsg, "Your details are saved under Draft number - DRAFT-");
		Reporter.log("CreatePurChaseRequest TESTCASE is PASSED--",true);
	}

}
