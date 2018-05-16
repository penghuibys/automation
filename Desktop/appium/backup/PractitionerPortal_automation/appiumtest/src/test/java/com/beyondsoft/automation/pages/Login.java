
package com.beyondsoft.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import frame.com.mtf.ibm.operation.Locate;

public class Login {
	public Login(AppiumDriver<WebElement> iOSDriver){
		Login.iOSDriver = iOSDriver;
	}
	
	public static AppiumDriver<WebElement> iOSDriver;

  public void signIn(String amwayId, String password) throws InterruptedException {
	  Locate locate = new Locate(iOSDriver);
	  
	  locate.send("安利号码", amwayId);
	  locate.send("密码", password);
	  locate.click("登   录");
  }
  
  
}











