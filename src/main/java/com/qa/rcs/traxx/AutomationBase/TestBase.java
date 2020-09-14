package com.qa.rcs.traxx.AutomationBase; 

import java.awt.Robot;
//import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
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
	//public static Logger log;
	



	public TestBase()//constructor
	{
		

			try {
				prop = new Properties();//create object of Properties Class & assign it prop global object reference variable
		
				//create object of FileInputStream Class to read data from configuration File
			
		
				FileInputStream fis = new FileInputStream("E:\\RCS TRAXX AUTOMATION\\RCS-TRAXX-AUTOMATION\\src\\main\\java\\com\\qa\\rcs\\traxx\\Configuration\\config.properties");
				System.out.println(fis);

				prop.load(fis);//load(read) data using FileInputStream object reference into 
							   //Properties object reference using load() method
				
				//System.out.println(prop);
			
		
				}catch(FileNotFoundException e) {
					e.printStackTrace();
						}
					catch(IOException  e) {
							e.printStackTrace();
						}
			
			//SECOND CONFIG FILE START  **************************
			/*try {
				prop2 = new Properties();//create object of Properties Class & assign it prop global object reference variable
		
				//create object of FileInputStream Class to read data from configuration File
			
		
				//FileInputStream fis2 = new FileInputStream("E:\\SVN-V3-automation\\Automation\\src\\main\\java\\com\\qa\\vidalhealth\\Configuration\\config2.properties");

				//prop2.load(fis2);//load(read) data using FileInputStream object reference into 
							   //Properties object reference using load() method
			
		
				}catch(FileNotFoundException e) {
					e.printStackTrace();
						}
					catch(IOException  e) {
							e.printStackTrace();
						}*/
			//SECOND CONFIG FILE END  **************************************
	}
	
	/*static ExtentTest test;
	static ExtentReports report;*/
	//INITIALIZATION method() to get URL,maximize window...
	
	/*@BeforeTest
	public static void startTest()

	{

report = new ExtentReports("E:\\SVN-V3-automation\\Automation\\SCREENSHOTS\\ExtentReportResults.html",true);

	test = report.startTest("ExtentReport");

	}*/
		
	@BeforeMethod
	public static void initialization() {

		String browserName = prop.getProperty("browser");//get browser from configuration File
		
		if(browserName.equals("chrome")){
		System.setProperty("webdriver.chrome.driver","E:\\SVN-V3-automation\\Automation\\Driverserver\\chromedriver.exe");
	    driver = new ChromeDriver();	
		}
	    
	    else if(browserName.equals("FF")) {
		System.setProperty("webdriver.gecko.driver","E:\\SVN-V3-automation\\Automation\\Driverserver"
				+ "\\geckodriver.exe");
	    driver = new FirefoxDriver();  
	    }
		
	    else if(browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver","E:\\SVN-V3-automation\\Automation\\Driverserver\\IEDriverServer.exe");
		    driver = new InternetExplorerDriver();
		    
		    }
	
	    
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); hardcoded values
	    //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); hardcoded values
	    driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICITLY_WAIT, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	    
	    String url=prop.getProperty("url");
	    driver.get(url);
	    
	    //driver.get(prop.getProperty("url"));
	    
	   LoginPage loginPage = new LoginPage();
	    //HomePage hp = new HomePage();
	/*	String loginCredentials []=TestUtil.loginCredentials();
		String un = loginCredentials[0];
		String pwd =loginCredentials[1];*/
		loginPage.login();
	    
	    
	    

	}
	
	
	/*@AfterTest
	
	
	public static void endTest()
	
		{
	
		report.endTest(test);
	
		report.flush();
	
		}*/
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
		//driver.close();
	}
	
	/*@AfterMethod
    public void getResult(ITestResult result) throws IOException
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            String screenShotPath = TestUtil.capture("LoginPageValidatio");
        	//String screenShotPath = eventFiringWebDriver.getScreenshotAs("E:\\SVN-V3-automation\\Automation\\SCREENSHOTS\\");
        	//String screenShotPath = eventFiringWebDriver.ca
            test.log(LogStatus.FAIL, result.getThrowable());
            test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
        }
        report.endTest(test);
    }*/
	
	@AfterSuite
	public void autoReport_MailTrigger() {
	 
		// email ID of Recipient. 
		String recipient_TO = "vija@vidalhealthtpa.com"; 
		String recipient_CC = "vija@vidalhealthtpa.com"; 
		String recipient_BCC = "vija@vidalhealthtpa.com"; 

		// email ID of Sender. 
		String sender = "vijay@vidalhealthtpa.com"; 

		// using host as localhost 
		String host = "10.1.0.3"; 

		// Getting system properties 
		Properties properties = System.getProperties(); 

		// Setting up mail server 
		properties.setProperty("mail.smtp.host", host); 

		// creating session object to get properties 
		Session session = Session.getDefaultInstance(properties); 

		try
		{ 
			// MimeMessage object. 
			MimeMessage message = new MimeMessage(session); 

			// Set From Field: adding senders email to from field. 
			message.setFrom(new InternetAddress(sender)); 

			// Set To Field: adding recipient's email to from field. 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient_TO));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient_CC));
			message.addRecipient(Message.RecipientType.BCC, new InternetAddress(recipient_BCC));

			   // Add the subject link
						message.setSubject("Automation Test Report");
			 
						// Create object to add multimedia type content
						BodyPart messageBodyPart1 = new MimeBodyPart();
			 
						// Set the body of email
						messageBodyPart1.setText("Hi,"		
								+"                                                                                                                                                                                                                                                                                                                                                         "
								+"                                                                                                                                                                                                                                                                                                                                                         "                              
								+ "Attached the automation Test report, Please go through & give your feedback                                                                                                                                                                                                                                                                             "
								+"                                                                                                                                                                                                                                                                                                                                                         "
								+ "This mail is triggered automatically, after Completing AUTOMATION TEST RUN.");
						
						
			 
						// Create another object to add another content
						MimeBodyPart messageBodyPart2 = new MimeBodyPart();
						MimeBodyPart messageBodyPart3 = new MimeBodyPart();
						//MimeBodyPart messageBodyPart4 = new MimeBodyPart();
						//MimeBodyPart messageBodyPart5 = new MimeBodyPart();
			 
						// Mention the file which you want to send
						String emailable_report = "E:\\SVN-V3-automation\\Automation\\test-output\\emailable-report.html";
						String extent_report = "E:\\SVN-V3-automation\\Automation\\test-output\\Extent.html";
			 
						// Create data source and pass the filename
						DataSource source1 = new FileDataSource(emailable_report);
						DataSource source2 = new FileDataSource(extent_report);
						//DataSource source1 = new FileDataSource(emailable_report);
						//DataSource source2 = new FileDataSource(extent_report);
			 
						// set the handler
						messageBodyPart2.setDataHandler(new DataHandler(source1));
						messageBodyPart3.setDataHandler(new DataHandler(source2));
						//messageBodyPart2.setDataHandler(new DataHandler(source1));
						//messageBodyPart3.setDataHandler(new DataHandler(source2));
			 
						// set the file
						messageBodyPart2.setFileName(emailable_report);
						messageBodyPart3.setFileName(extent_report);
						
			 
						// Create object of MimeMultipart class
						Multipart multipart = new MimeMultipart();
			 
						// add body part 1
						multipart.addBodyPart(messageBodyPart1);
			 
						// add body part 2
						multipart.addBodyPart(messageBodyPart2);
						
						multipart.addBodyPart(messageBodyPart3);
			 
						// set the content
						message.setContent(multipart);
						
						/*try {
							java.awt.Desktop.getDesktop().browse(new URI("file:"+emailable_report));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							java.awt.Desktop.getDesktop().browse(new URI("file:"+extent_report));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
			 

			// Send email. 
			Transport.send(message); 
			System.out.println("Mail successfully sent"); 
		} 
		catch (MessagingException mex) 
		{ 
			mex.printStackTrace(); 
		} 
	} 
	
		//
		
	}
	
	


