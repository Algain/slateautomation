package slate.seleniumappium.automation.slate.seleniumappium.automation;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.testng.annotations.*;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverConfig {
	//Directory
	static String workingcampaign;
	static String workingdir = System.getProperty("user.dir");
	static String apkpath = workingdir + "/apk/Todoist_v12.8_apkpure.com.apk"; 
	static String log = workingdir + "/test-log/";
	static String screencapture = workingdir + "/screencapture/";
	
	//AppDetails
	static String automationName = "AstroAppTestAutomation";
	static String deviceName = "AndroidTestDeviceV5";
	static String appPackage = "com.todoist";
	static String appActivity = "com.todoist.activity.WelcomeActivity";
	static String appiumserverurl = "http://127.0.0.1:4723/wd/hub";
	public static URL serverurl;
	public static DesiredCapabilities capabilities;
	public static AndroidDriver<MobileElement> driver;

	@BeforeClass
	public static void setup() throws IOException {

		final String appiumurl = appiumserverurl;
		serverurl = new URL(appiumurl);
		//Configure capabilities
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		capabilities.setCapability(MobileCapabilityType.APP, apkpath);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

		driver = new AndroidDriver<MobileElement>(serverurl, capabilities);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.resetApp();

	}
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
