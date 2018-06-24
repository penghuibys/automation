package com.beyondsoft.automation.pages.android;


import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.log.LogUtil;
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
		SysUtil.sleep(10);
		if(isSearchBoxDisplayed()){
			androidDriver.findElementByXPath("//android.widget.EditText").click();
			androidDriver.findElementByXPath("//android.widget.EditText").sendKeys("3959");
			LogUtil.step("Send Key '39597' to searchbox, Passed");
		} else {
			System.out.println("keyboard input");
			androidDriver.tap(1, 449, 288, 1);
			SysUtil.sleep(2);
			androidDriver.getKeyboard().sendKeys("7");
			androidDriver.getKeyboard().sendKeys("9");
			androidDriver.getKeyboard().sendKeys("5");
			androidDriver.getKeyboard().sendKeys("9");
			androidDriver.getKeyboard().sendKeys("3");
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
	
	private boolean isSearchBoxDisplayed() {
		try {
			boolean isDisplayed = androidDriver.findElementByXPath("//android.widget.Button[@content-desc='搜索']/preceding-sibling::android.widget.EditText").isDisplayed();
			System.out.println(isDisplayed);
			return isDisplayed;
		} catch (Exception e) {
			System.out.println(false);
			return false;
		}
	}

}
