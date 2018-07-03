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
		
		locate.click("个人护理");
		SysUtil.sleep(10);
//		androidDriver.tap(1, 1008, 280, 1); // P8
		androidDriver.tap(1, 1019, 282, 1); // Mate9

//		locate.click("搜索");
		SysUtil.sleep(5);
		if(isSearchBoxDisplayed()){
			androidDriver.findElementByXPath("//android.widget.EditText").click();
			androidDriver.findElementByXPath("//android.widget.EditText").sendKeys("97");
			androidDriver.findElementByXPath("//android.widget.EditText").sendKeys("59");
			androidDriver.findElementByXPath("//android.widget.EditText").sendKeys("55");
			androidDriver.findElementByXPath("//android.widget.EditText").sendKeys("39");
			LogUtil.step("Send Key '39597' to searchbox, Passed");
		} else {
			System.out.println("keyboard input");
			androidDriver.tap(1, 449, 288, 1);
			SysUtil.sleep(5);
			androidDriver.getKeyboard().sendKeys("7");
			androidDriver.getKeyboard().sendKeys("9");
			androidDriver.getKeyboard().sendKeys("5");
			androidDriver.getKeyboard().sendKeys("9");
			androidDriver.getKeyboard().sendKeys("3");
			LogUtil.step("Keyboard Send Key '39597' to searchbox, Passed");
		}
		SysUtil.sleep(5);
		locate.click("婴儿沐浴露");
		SysUtil.sleep(15);

		// bug: always pop up once the page refreshes
		locate.clickIfItemDisplayed("我知道了1");
		locate.clickIfItemDisplayed("我知道了");
		SysUtil.sleep(2);
	}
	
	public void setQuantity() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.swipeAction("up");
		SysUtil.sleep(2);
		locate.click("数量添加");
		SysUtil.sleep(2);
		locate.swipeAction("down");
	}
	
	private boolean isSearchBoxDisplayed() {
		try {
			boolean isDisplayed = androidDriver.findElementByXPath("//android.widget.EditText").isDisplayed();
			System.out.println(isDisplayed);
			return false;
		} catch (Exception e) {
			System.out.println(false);
			return false;
		}
	}
	
//	private boolean waitForSearchBoxDislayed() {
//		for (int i = 0; i < 2; i ++) {
//			if (isSearchBoxDisplayed()) {
//				return true;
//			} else {
//				SysUtil.sleep(2);
//				System.out.println("Retry wait for searchBoxDislayed");
//				androidDriver.tap(1, 30, 282, 1);
//				SysUtil.sleep(2);
//				androidDriver.tap(1, 1019, 282, 1);
//				SysUtil.sleep(2);
//			}
//		}
//		return false;
//	}

}
