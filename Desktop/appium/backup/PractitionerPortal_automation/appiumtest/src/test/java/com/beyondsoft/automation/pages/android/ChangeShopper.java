package com.beyondsoft.automation.pages.android;



import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.base.Validation;

import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.base.TestBase;
import frame.com.pp.auto.log.LogUtil;
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
//		androidDriver.findElementByXPath("//android.view.View[contains(@content-desc,'更改')]").click();
//		androidDriver.tap(1, 989, 408, 1);//更改  P8
//		androidDriver.tap(1, 989, 382, 1);//更改  
		locate.clickIfItemDisplayed("更改");
		SysUtil.sleep(5);
		locate.click("常用购货人按钮");
		locate.click("选择常用购货人");
		locate.click("确定更改常用购货人");
		locate.click("确定常用购货人");
		SysUtil.sleep(15);
//		androidDriver.tap(1, 811, 950, 1);//确定
	}
	
	public String getCurrentShopper() throws InterruptedException {
		SysUtil.sleep(5);
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("当前购货人1");
		
	}


}
