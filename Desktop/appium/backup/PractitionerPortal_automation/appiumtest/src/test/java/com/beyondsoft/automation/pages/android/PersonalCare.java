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

	// 添加婴儿沐浴露
	public void searchBabyShampoo() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);

		androidDriver.tap(1, 1008, 298, 1); // P8
//		androidDriver.tap(1, 1014, 282, 1); // Mate9
		SysUtil.sleep(5);
		try {
			locate.send("商品输入框", "39597");
		} catch (Exception e) {
			SysUtil.sleep(5);
			locate.send("商品输入框", "39597");
		}
		
		SysUtil.sleep(2);
		locate.click("婴儿沐浴露");
		SysUtil.sleep(10);

		// bug: always pop up once the page refreshes
		locate.clickIfItemDisplayed("我知道了1");
		locate.clickIfItemDisplayed("我知道了");
		SysUtil.sleep(5);
	}
	
	public void setQuantity() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.swipeAction("up");
		locate.click("数量添加");
		locate.swipeAction("down");
	}

}
