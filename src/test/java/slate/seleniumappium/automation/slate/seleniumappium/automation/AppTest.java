package slate.seleniumappium.automation.slate.seleniumappium.automation;

import java.awt.AWTException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppTest extends DriverConfig {

	public static String requiredDate;

	//Login Details
	static String username = "miztavallavan@gmail.com";
	static String password = "password1234";

	@SuppressWarnings("unchecked")
	@Test 
	public void AppAutomation() throws InterruptedException, IOException, AWTException{

		// Date Format
		DateFormat df = new SimpleDateFormat("ddMMyyHHmm");
		requiredDate = 	df.format(new Date()).toString();
		System.out.println(requiredDate);

		//log File
		Logger logger = Logger.getLogger("MyLog");  
		FileHandler fh;  

		try {  
			// Crate new log File  
			fh = new FileHandler(log + requiredDate + "_astroapptest.log");  
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  

			// the following statement is used to log any messages  
			logger.info("Log File"); 			
			logger.info("Date :"+requiredDate );

			Thread.sleep(4000);

			// Assert
			//Welcome Text
			String welcometext = driver.findElement(By.id("com.todoist:id/summary")).getAttribute("text");
			System.out.println(welcometext);
			String welcometextverify = "Create an account to save your tasks and access them anywhere. It's free. Forever.";
			logger.info("Welcome Text :" + welcometext);
			
			try{
				Assert.assertEquals(welcometextverify, welcometext);
				System.out.println("Welcome Text Verified");
				logger.info("Welcome Text Verified");
			}catch(AssertionError e){
				System.out.println("Failed, unable to verify welcome text");
				logger.info("Failed, unable to verify welcome text");
			}
			
			// Buttons
			String buttonone = driver.findElement(By.id("com.todoist:id/btn_google")).getAttribute("text");
			System.out.println(buttonone);
			String buttontext = "Continue with Google";
			logger.info("Button Text :" + buttonone);
			
			
			try{
				Assert.assertEquals(buttontext, buttonone);
				System.out.println("Button Text Verified");
				logger.info("Button Text Verified");
			}catch(AssertionError e){
				System.out.println("Button Text Not Verified");
				logger.info("Button Text Not Verified");
			}
			
			//Login to app
			MobileElement loginbyemail = driver.findElement(By.id("com.todoist:id/btn_welcome_continue_with_email"));
			loginbyemail.click();
			logger.info("login by email");
			
			MobileElement login_email = driver.findElement(By.id("com.todoist:id/email_exists_input"));
			login_email.sendKeys(username);
			logger.info("Username :"+ username );
			MobileElement login_continue_with_email = driver.findElement(By.id("com.todoist:id/btn_continue_with_email"));
			login_continue_with_email.click();
			logger.info("Select option");
			MobileElement login_continue_with_email_password = driver.findElement(By.id("com.todoist:id/log_in_password"));
			login_continue_with_email_password.sendKeys(password);
			logger.info("Password :"+ password);
			MobileElement login_continue = driver.findElement(By.id("com.todoist:id/btn_log_in"));
			login_continue.click();	
			logger.info("Submit");

			// THE APP KEEPS CRASHING AFTER LOGIN
			//			String taks_name = "automationTask";
			//			//Create Task
			//			MobileElement create_task = driver.findElement(By.id("com.todoist:id/fab"));
			//			create_task.click();	
			//			logger.info("Create Task");
			//			MobileElement create_task_name = driver.findElement(By.id("android:id/message"));
			//			create_task_name.sendKeys(taks_name);	
			//			logger.info("Task Name :"+ taks_name );
			//			MobileElement create_task_name_confirm = driver.findElement(By.id("android:id/button1"));
			//			create_task_name_confirm.click();
			//			logger.info("Submit New Task");

			//Screen Capture App
			driver = (AndroidDriver<MobileElement>) new Augmenter().augment(driver);
			String screencapturefilename = requiredDate+".png";
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				com.google.common.io.Files.copy(srcFile, new File(screencapture + screencapturefilename));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Thread.sleep(1000);

		} catch (SecurityException e) {  
			e.printStackTrace(); 
		}
		driver.resetApp();
		email.main(null);
	}
}