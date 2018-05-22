package com.beyondsoft.automation.pages.android;


import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.util.SysUtil;
import io.appium.java_client.AppiumDriver;


public class PersonalCare {
	
	public static AppiumDriver<WebElement> androidDriver;
	
	public PersonalCare(AppiumDriver<WebElement> androidDriver){
		PersonalCare.androidDriver = androidDriver;
	}

	//添加婴儿沐浴露
	public void searchBabyShampoo() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		SysUtil.sleep(2);
		try {
			if (androidDriver.findElementById("searchBtn").isDisplayed()){
				locate.send("商品输入框", "39597");
			}
		} catch (Exception e) {
			WebElement search = androidDriver.findElementsByAccessibilityId("@").get(0);
			SysUtil.sleep(2);
			search.click();
		}
		locate.send("商品输入框", "39597");
		androidDriver.findElementById("searchBtn").click();

		locate.atScreen("婴儿沐浴露");
	}
	
	public void setQuantity() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
	//	locate.send("数量", "2"); Not working
		locate.click("数量添加");
	}

}
