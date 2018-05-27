
package com.beyondsoft.automation.base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import frame.com.mtf.ibm.init.AndroidInit;
import frame.com.mtf.ibm.init.IOSInit;
import frame.com.pp.auto.base.TestBase;
import java.io.File;
import java.net.MalformedURLException;
import com.beyondsoft.automation.model.UserInfo;



import mobile.appiumtest.Utilities;


public class Setup extends TestBase{
	
	public static String amwayId;
	public static String password;
	public AndroidDriver<WebElement> androidDriver;
	public AndroidInit android;
	public IOSInit ios;

  
  public void setUpAndroidDriver() throws MalformedURLException{
	  	android = new AndroidInit();
	  	androidDriver = android.launchApp();

	  	setMobileDriver(androidDriver); 
  }
  
  
  public void setUpIosDriver() throws MalformedURLException{
	  	ios = new IOSInit();
	  	
	  	iosDriver = ios.launchRealApp();
	  	setMobileDriver(iosDriver); 


  }

  public void setUpUser() throws MalformedURLException{
		File app = new File("test-data/" + "user.json");
	  	UserInfo user = Utilities.load(app.getAbsolutePath(), "user", UserInfo.class);
		amwayId = user.getAmwayId();
		password = user.getPassword();
  }
	

  
}











