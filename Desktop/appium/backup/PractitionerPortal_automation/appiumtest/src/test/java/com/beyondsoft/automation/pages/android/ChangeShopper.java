package com.beyondsoft.automation.pages.android;


import java.util.List;

import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.base.Validation;

import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.base.TestBase;
import io.appium.java_client.AppiumDriver;
import frame.com.pp.auto.util.SysUtil;


public class ChangeShopper extends TestBase{
	
	public static AppiumDriver<WebElement> androidDriver;
	
	public ChangeShopper(AppiumDriver<WebElement> androidDriver){
		ChangeShopper.androidDriver = androidDriver;
	}
	
	public void changeShopperAndConfirm() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("更改");
		try {
			SysUtil.sleep(10);
			WebElement element = androidDriver.findElementByXPath("(//android.view.View[@content-desc='*'])[1]");
			element.click();
		} catch (Exception e) {
			try {
				androidDriver.findElementById("colorbox").click();
			} catch (Exception e2) {
				androidDriver.findElementByXPath("//android.view.View[contains(@content-desc,'您当前可用悦享分')]").click();
			}
		} finally {
			androidDriver.findElementByXPath("(//android.view.View[@content-desc='*'])[1]").click();
			SysUtil.sleep(5);
		}
		locate.click("梅博众");
		try {
			androidDriver.findElementByXPath("//android.view.View[@content-desc='确定']").click();//确认选择
		} catch (Exception e) {
			SysUtil.sleep(5);
			List<WebElement> eles = androidDriver.findElementsByAccessibilityId("确定");
			eles.get(0).click();
		}
		locate.click("保存为常用购货人");
		SysUtil.sleep(5);
		androidDriver.findElementByXPath("//android.view.View[@content-desc='确定']").click();//确认更改
		SysUtil.sleep(20);
	}
	
	public String getCurrentShopper() throws InterruptedException {
		SysUtil.sleep(5);
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("当前购货人1");
		
	}


}
