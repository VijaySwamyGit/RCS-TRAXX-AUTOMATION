package com.qa.rcs.traxx.AutomationBase; 

import java.awt.Robot;
//import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.rcs.traxx.AutomationUtils.TestUtil;
import com.qa.rcs.traxx.WebPages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


//TESTBASE CLASS to define commonly used methods()

public class TestBase {
	public static WebDriver driver;//driver global object reference variable
	public static Properties prop;//prop global object reference variable
	public static Properties prop2;//prop global object reference variable
	public static EventFiringWebDriver eventFiringWebDriver;//eventFiringWebDriver global object reference variable
	
	public static ExtentTest test;
	public static ExtentReports report;
	public static Robot robot;

	public TestBase()//constructor
	{
		

			try {
				prop = new Properties();//create object of Properties Class & assign it prop global object reference variable
		
				//create object of FileInputStream Class to read data from configuration File
			
		
				FileInputStream fis = new FileInputStream("E:\\RCS TRAXX AUTOMATION\\RCS-TRAXX-AUTOMATION\\src\\main\\java\\com\\qa\\rcs\\traxx\\Configuration\\config.properties");
			

				prop.load(fis);//load(read) data using FileInputStream object reference into 
							   //Properties object reference using load() method
				
			
			
		
				}catch(FileNotFoundException e) {
					e.printStackTrace();
						}
					catch(IOException  e) {
							e.printStackTrace();
						}
			
			
	}
	
	
		
	@BeforeMethod
	public static void initialization() {

		String browserName = prop.getProperty("browser");//get browser from configuration File
		
		if(browserName.equals("chrome")){
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			options.addArguments("start-maximized"); 
		    options.addArguments("enable-automation"); 
		    options.addArguments("--no-sandbox"); 
		    options.addArguments("--disable-infobars"); 
		    options.addArguments("--disable-dev-shm-usage"); 
		    options.addArguments("--disable-browser-side-navigation"); 
		    options.addArguments("--disable-gpu");
		System.setProperty("webdriver.chrome.driver","E:\\RCS TRAXX AUTOMATION\\RCS-TRAXX-AUTOMATION\\Driverserver\\chromedriver.exe");
	    driver = new ChromeDriver();	
		}
	    
	    else if(browserName.equals("FF")) {
		System.setProperty("webdriver.gecko.driver","E:\\RCS TRAXX AUTOMATION\\RCS-TRAXX-AUTOMATION\\Driverserver\\geckodriver.exe");
	    driver = new FirefoxDriver();  
	    }
		
	    else if(browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver","E:\\SVN-V3-automation\\Automation\\Driverserver\\IEDriverServer.exe");
		    driver = new InternetExplorerDriver();
		    
		    }
	
	
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITLY_WAIT, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	    
	    String url=prop.getProperty("url");
	    driver.get(url);
	    
	    String un = prop.getProperty("userid");
	    String pwd = prop.getProperty("password");
	    
	    LoginPage loginPage = new LoginPage();
	  
		loginPage.login(un, pwd);   

	}
	

	
	@AfterMethod
	public void teardown() {
		driver.quit();
		//driver.close();
	}
	

		
	}
	
	


