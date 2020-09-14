package com.qa.rcs.traxx.AutomationUtils;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.rcs.traxx.AutomationBase.TestBase;
import com.qa.rcs.traxx.ExcelUtilities.ExcelUtilities;
//import com.qa.vidalhealth.Pages.HomePage;
//import com.qa.vidalhealth.Pages.HomePage3;
//import com.qa.vidalhealth.Pages.LoginPage;

public class TestUtil extends TestBase {
	
	//String parentWindow1 = driver.getWindowHandle();
	
	public static long PAGE_LOAD_TIMEOUT=35;//Used in TestBase class to define page_load_timeout
	public static long IMPLICITLY_WAIT=35;//Used in TestBase class to define Implicit wait
	static int assignIconIndex=1;
	
ExcelUtilities excelUtilities;
	
	String FileNameDP="PreauthGEDPageTestDataUsingDP";
	
	String SheetNameDP="sheet1";
	
	
	
	//method() to get Login credentials from configuration File
	
		public static String [] loginCredentials() {//get Login Credentials from configuration File
			

			String loginCredentials [] = new String [2];
			loginCredentials[0]=prop.getProperty("userid");
			loginCredentials[1]=prop.getProperty("password");
			return loginCredentials;
		}
		
		
		//Method()-1 to Take Screenshot
		
		public static void takeScreenshot(String screenName) 
		{
			System.out.println("----take screen---");
			 Date date = new Date(System.currentTimeMillis());
	         String dateString = date.toString();
	         dateString = dateString.replaceAll("[^a-zA-Z0-9]", "");
			 eventFiringWebDriver = new EventFiringWebDriver(driver);
			 File f1 = eventFiringWebDriver.getScreenshotAs(OutputType.FILE);
			 File f2 = new File("E:\\RCS TRAXX AUTOMATION\\RCS-TRAXX-AUTOMATION\\ScreenShots\\"+ screenName +" - "+ dateString +".png");
			 try {
				 	FileUtils.copyFile(f1, f2);
			 	 }catch (IOException e) {
			 		 						e.printStackTrace();
			 	 						}
		}

		//Method()-2 to Take Screenshot
			     
			    public static String capture(String screenName)
			    {
			        TakesScreenshot ts = (TakesScreenshot)driver;
			        File source = ts.getScreenshotAs(OutputType.FILE);
			        String dest = ("E:\\RCS TRAXX AUTOMATION\\RCS-TRAXX-AUTOMATION\\ScreenShots\\"+screenName+".png");
			        System.out.println("dest--"+dest);
			        File destination = new File(dest);
			        try {
							FileUtils.copyFile(source, destination);
						}catch (IOException e) {
													e.printStackTrace();
											   }        			                     
			        return dest;
			    }


		public static void handleAcceptAlert() 
		{

			TestUtil.delayToSync();		
			String popUpstr = driver.switchTo().alert().getText();
			System.out.println("popUpstr--"+popUpstr);
			driver.switchTo().alert().accept();		
		}
		
       public void handleDismissAlert() 
       {
			
			driver.switchTo().alert().getText();
			driver.switchTo().alert().dismiss();
			
	   }
       
       
       public static void delayToSync()
       {
    	   try {
    		   		Thread.sleep(1500);
    	   	   }catch (InterruptedException e) {
    	   		   									e.printStackTrace();
    	   	   									}
    	}
       
       
       //Date Incrementer STARTS
       
       public  static String dateIncrementer(String oldDate)
       {

   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   			Calendar c = Calendar.getInstance();
   			try{
   					//Setting the date to the given date
   					c.setTime(sdf.parse(oldDate));
   			   }catch(ParseException e){
   				   							e.printStackTrace();
   			   						   }
   		   
   			//Number of Days to add
   			c.add(Calendar.DAY_OF_MONTH, 1);  
   			//Date after adding the days to the given date
   			String newDate = sdf.format(c.getTime());  
   			//Displaying the new Date after addition of Days
   			//String newDateTime = newDate+" "+"11:00 AM";
   			String newDateTime = newDate;
   		
   			return newDateTime;
   	   }
       //Date Incrementer ENDS
       
   
       
       public static String windowHandles() 
       {

    	   	Set<String> windows = driver.getWindowHandles();
    	   	Iterator<String> itr = windows.iterator();

    	   	//patName will provide you parent window
    	   	String patName = itr.next();
    	  
    	   	//chldName will provide you child window
    	   	String chldName = itr.next();
    	   	
    	   	//Switch to child window
    	   	driver.switchTo().window(chldName);


    	   	return chldName;
       }
       
    
    public static int stringToInt(String str) 
    {
    	
    	int ii=Integer.parseInt(str);
    	return ii;

    }
    
    //Select date by JS starts
    
    public static void selectDateByJS(WebDriver driver, WebElement element, String dateValue) 
    {
    	
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].setAttribute('value','"+dateValue+"');",element);

    }
    
  //Select date by JS starts
    
    public static void scrollUp() 
    {
    	
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-500)", "");

    }
  
    public static void scrollUpMini() 
    {
  	
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-300)", "");
	
    }
    
    public static void scrollDownMini() 
    {
 
	  	JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
   }
  
    public static void scrollDown() 
    {

    	JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)", "");
    }
  
 
  
    //REFERENCE NUMBER STARTS
  
  
    //method to get xpath of navigational Pages for "TO DO TASK" webtable S
	public static WebElement navigationalPageXpathToDoTask(int pageIndex) 
	{
		String navigationalPageXpath="";
		String s1= "//a[@onclick=\"javascript:pageIndex(";
		String s2= pageIndex+"";
		String s3= ",'tDSelfAssignment')\"]";
		navigationalPageXpath=navigationalPageXpath+s1+s2+s3;
		WebElement toDoTaskTableNavigationalPage =driver.findElement(By.xpath(navigationalPageXpath));
		return toDoTaskTableNavigationalPage;
	}
	//method to get xpath of navigational Pages for "TO DO TASK" webtable E
	
	
	//****************************************************************************************
	//method to get xpath of user assignment ICON for "DATA ENTRY" webtable S
	
		public static void userAssignmentIconXpath(int index) 
		{
			TestUtil.scrollDown();
			TestUtil.scrollDown();
			int indexTemp=index;
			index = (index*5)-assignIconIndex;
			String userAssignmentIconXpath="";
			String s1= "//a[@onclick='onUserAssignment(";
			String s2= index+"";
			String s3= ")']";
			userAssignmentIconXpath=userAssignmentIconXpath+s1+s2+s3;
			try
			{
			WebElement userAssignmentIconWebElement =driver.findElement(By.xpath(userAssignmentIconXpath));
			userAssignmentIconWebElement.click();
			
			}catch(Exception ee){
									if(indexTemp != index)
									{
										assignIconIndex++;
										userAssignmentIconXpath(indexTemp); //recursive method call
									}
								}	

		}
		//method to get xpath of user assignment ICON for "DATA ENTRY" webtable E
		
		//********************************************************************************************
		
		//method to get xpath of navigational Pages for "DATA ENTRY" webtable S
		static int pagenewStatic  = 0;
		
		public static void s()
		{
			pagenewStatic = 0;
		}

		public static int navigationalPageXpathDataEntry(int pageIndex) 
		{
			
			String navigationalPageXpathDE="";
			String s1= "//a[@onclick=\"javascript:pageIndex(";
			String s2= pageIndex+"";
			String s3= ",'tableData')\"]";
			navigationalPageXpathDE=navigationalPageXpathDE+s1+s2+s3;
			System.out.println("navigationalPageXpathDE--"+navigationalPageXpathDE);
			
			try
			{
				WebElement dataEntryTableNavigationalPage =driver.findElement(By.xpath(navigationalPageXpathDE));
				dataEntryTableNavigationalPage.click();
			    //TestUtil.scrollDown();
				//TestUtil.scrollDown();
				//return pageIndex;
				
			}catch(Exception e) {

									if(pageIndex != 0)
									{	
										

										pageIndex--;		
				             		    pagenewStatic++;//number of times it enters the if condition
										System.out.println("pageIndex: " + pageIndex);
										TestUtil.navigationalPageXpathDataEntry(pageIndex); //recursive method call
									
									}
								
								}
			if(pageIndex==10)
			{
				return pageIndex;
			}else {
				return pageIndex-(pagenewStatic-1);
			}
			
			
			
			
			//it is always returning "9" because of recursive method call so subtracting (pagenewStatic-1) it is TEMPORARY SOLUTION need to
										 //understand how to handle recursive methods returning value
			
			
			//return pageIndex;
		}
	
		//method to get xpath of navigational Pages for "DATA ENTRY" webtable E   
		
		
		//
		public  void readDateForDataProvider()
		{
			//get old date & increase it by one day and write back to excel
			int lastRowNum1=excelUtilities.rowCount(FileNameDP, SheetNameDP);
			String oldDateOfAdmission=null;
			try 
			{
				oldDateOfAdmission = excelUtilities.readDOAFromExcelForDataProvider();
			} 	
			catch (Exception e2) 
			{
			
				e2.printStackTrace();
			}
				
			String newDateTime = TestUtil.dateIncrementer(oldDateOfAdmission);		
			try {
					excelUtilities.writeToExceloldcodeForDataProvider(newDateTime, FileNameDP, SheetNameDP);//write incremented date in excel
					TestUtil.delayToSync();
					TestUtil.delayToSync();
				} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		//
		
		
       
       
}
