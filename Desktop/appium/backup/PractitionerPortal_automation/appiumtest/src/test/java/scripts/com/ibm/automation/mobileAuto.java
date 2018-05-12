
package scripts.com.ibm.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import frame.com.mtf.ibm.init.AndroidInit;
import frame.com.mtf.ibm.init.IOSInit;
import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.base.TestBase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.CapabilityType;
//import com.appium.ios.IOSDeviceConfiguration;
//import com.appium.utils.CommandPrompt;



public class mobileAuto extends TestBase{
	
	public String username;
	public String password;
	public String username_l;
	public String password_l;
	public String year1;
	public String year2;
	public AndroidDriver<WebElement> driver;
	public AndroidInit android;
	public IOSDriver<WebElement> iosDriver;
	public IOSInit ios;
    public WebDriver mobileDriver = null;

  
  @BeforeTest
  public void setUp() throws MalformedURLException{
	  	android = new AndroidInit();
	  	ios = new IOSInit();
	  	
	//  	driver = android.launchApp();
	  	iosDriver = ios.launchRealApp();
	  	setMobileDriver(iosDriver); 
	  	
		data.loadData("testdata.xlsx", "mobile");
		username = data.getData("mobileTest", "username");
		password = data.getData("mobileTest", "password");
		
	 // 	username_l = data.getData("mobileTest", "username_l");
	//	password_l = data.getData("mobileTest", "password_");
  }


	
  @Test//(description = "login")
  public void mobileTestDemo() throws InterruptedException, MalformedURLException {
	  
	  
		
	  username_l = "//XCUIElementTypeTextField";
	  password_l = "//XCUIElementTypeSecureTextField";
	  
//	  Locate.swipeAction("left");;
  
//	  Locate.send(iosDriver, username_l, username);
//	  Locate.send(iosDriver, password_l, password);

	  Locate.send(iosDriver, "username", username);
	  Locate.send(iosDriver, "password", password);
	  
	  
  }
  
  
}











