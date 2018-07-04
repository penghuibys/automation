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
//		locate.click("更改");
		try {
			WebElement ele = androidDriver.findElementByXPath("//android.view.View[contains(@content-desc,'更改')]");
			int x = ele.getLocation().getX();
			int y = ele.getLocation().getY();
			System.out.println("更改x:"+x);
			System.out.println("更改y:"+y);
			androidDriver.tap(1, x, y, 1);
		} catch (Exception e) {
			SysUtil.sleep(2);
			androidDriver.tap(1, 989, 410, 1);//更改  
		}
		
		SysUtil.sleep(10);
		try {
			androidDriver.findElementByAccessibilityId("常用购货人").isDisplayed();
		} catch (Exception e) {
			androidDriver.tap(1, 56, 288, 1);
			SysUtil.sleep(2);
			androidDriver.tap(1, 989, 410, 1);//更改  
			SysUtil.sleep(5);
		}
//		androidDriver.tap(1, 989, 408, 1);//更改  P8
//		androidDriver.tap(1, 989, 410, 1);//更改  
//		locate.clickIfItemDisplayed("更改");
		locate.click("常用购货人按钮");
		SysUtil.sleep(2);
		try {
			androidDriver.findElementByXPath("//android.view.View[@content-desc='选择常用购货人']/../following-sibling::android.view.View//android.widget.EditText").isDisplayed();
		} catch (Exception e) {
			androidDriver.tap(1, 920, 851, 1);
			LogUtil.step("Tap '常用购货人按钮', Passed");
			SysUtil.sleep(5);
		}
		androidDriver.tap(1, 388, 1592, 2);
//		locate.click("选择常用购货人");
		locate.click("确定更改常用购货人");
		
		SysUtil.sleep(2);
		locate.click("确定常用购货人");
		SysUtil.sleep(15);
		
		try {
			androidDriver.findElementByXPath("//android.view.View[contains(@content-desc,'当前购货人')]/following-sibling::android.view.View").isDisplayed();
		} catch (Exception e) {
			androidDriver.tap(1, 106, 1733, 1);
			SysUtil.sleep(15);
		}
	}
	
	public String getCurrentShopper() throws InterruptedException {
		SysUtil.sleep(5);
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("当前购货人1");
		
	}


}
