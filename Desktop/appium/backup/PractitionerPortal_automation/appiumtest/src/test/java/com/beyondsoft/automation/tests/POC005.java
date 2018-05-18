
package com.beyondsoft.automation.tests;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import frame.com.mtf.ibm.init.IOSInit;
import frame.com.mtf.ibm.init.AndroidInit;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.base.TestBase;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import com.beyondsoft.automation.pages.Login;
import com.beyondsoft.automation.pages.MainPage;
import com.beyondsoft.automation.pages.RichJayCoffee;
import com.beyondsoft.automation.pages.Exchange;
import com.beyondsoft.automation.model.userInfo;
import mobile.appiumtest.Utilities;
import frame.com.mtf.ibm.operation.Locate;


public class POC005 extends TestBase{
	
	public String amwayId;
	public String password;
	public IOSDriver<WebElement> iosDriver;
	public AndroidDriver<WebElement> androidDriver;
	public IOSInit ios;
	public AndroidInit android;
    public WebDriver mobileDriver = null;

  
  @BeforeTest
  public void setUp() throws MalformedURLException{
	  	ios = new IOSInit();
	  	android = new AndroidInit();
	  	
	  	androidDriver = android.launchApp();
//	  	List<WebElement> ele = androidDriver.findElementsByAccessibilityId("搜索");
//	  	ele.get(0).click();
//	  	WebElement elem = androidDriver.findElementById("com.tencent.mm:id/hx");
//	  	elem.sendKeys("安利");
	  	
	  	
	  	setMobileDriver(androidDriver); 
		File app = new File("test-data/" + "user.json");
	  	userInfo user = Utilities.load(app.getAbsolutePath(), "user", userInfo.class);

		amwayId = user.getAmwayId();
		password = user.getPassword();
  }


	
  @Test
  public void mobileTestDemo() throws InterruptedException, MalformedURLException {
	  Locate locate = new Locate(null, androidDriver);
	  locate.click_("搜索");
	  locate.send("输入框", "安利");
	  
	  
	  
	  
	  
	  
  }
  
}











