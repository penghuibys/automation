package com.beyondsoft.automation.pages;

import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import io.appium.java_client.AppiumDriver;

public class RichJayCoffee {
	
	public static AppiumDriver<WebElement> iOSDriver;
	
	public RichJayCoffee(AppiumDriver<WebElement> iOSDriver){
		RichJayCoffee.iOSDriver = iOSDriver;
	}
	
	public void setExchange () throws InterruptedException {
		  Locate locate = new Locate(iOSDriver, null);
		  locate.click("RICHJAY咖啡饮品券");
		  locate.atScreen("立即兑换");
		  locate.swipeAction("up");
		  locate.click("悦享分+现金");
		  locate.send("购买数量", "2");
		  locate.click("悦享分+现金"); //workaround to dismiss keyboard
	}


}
