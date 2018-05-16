
package com.beyondsoft.automation.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import frame.com.mtf.ibm.operation.Locate;

public class Login {
	public Login(AppiumDriver<WebElement> iOSDriver){
		Login.iOSDriver = iOSDriver;
	}
	
	public static AppiumDriver<WebElement> iOSDriver;

  public void signIn(String username, String password) throws InterruptedException {
	  Locate locate = new Locate(iOSDriver);
	  
	  locate.send("username", "58374544");
	  locate.send("password", "123456");
	  locate.click("登   录");
  }
  
  
}











