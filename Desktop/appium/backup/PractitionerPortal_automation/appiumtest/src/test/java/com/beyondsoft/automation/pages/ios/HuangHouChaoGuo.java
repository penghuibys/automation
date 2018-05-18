package com.beyondsoft.automation.pages.ios;

import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.pages.ios.ShoppingCart;

import frame.com.mtf.ibm.operation.Locate;
import io.appium.java_client.AppiumDriver;

public class HuangHouChaoGuo {
	
	public static AppiumDriver<WebElement> iOSDriver;
	
	public HuangHouChaoGuo(AppiumDriver<WebElement> iOSDriver){
		HuangHouChaoGuo.iOSDriver = iOSDriver;
	}
	
	public void addToShoppingCart() throws InterruptedException {
		  Locate locate = new Locate(iOSDriver, null);
		  ShoppingCart shoppingCart = new ShoppingCart(iOSDriver);
		  locate.click("皇后中式炒锅");
		  shoppingCart.addToShoppingCart();
	}
	



}
