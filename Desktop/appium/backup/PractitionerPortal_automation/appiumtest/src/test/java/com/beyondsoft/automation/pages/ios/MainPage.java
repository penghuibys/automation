package com.beyondsoft.automation.pages.ios;

import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.util.SysUtil;
import io.appium.java_client.AppiumDriver;

public class MainPage {
	
	public static AppiumDriver<WebElement> iOSDriver;
	
	public MainPage(AppiumDriver<WebElement> iOSDriver){
		MainPage.iOSDriver = iOSDriver;
	}
	
	public void navigateToAmywayCloudShopping() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.click("安利云购");
		SysUtil.sleep(5);
		locate.clickIfItemDisplayed("我知道了");
	}

	public void searchRichJayCoffee() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.click("搜索");
		locate.send("输入框", "饮品券");
		locate.click("搜索");
		locate.atScreen("RICHJAY咖啡饮品券");
	}
	
	public void searchHuangHouChaoGuo() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		SysUtil.sleep(5);
		locate.click("搜索");
		try {
			locate.send("输入框", "皇后中式炒锅");
		} catch (Exception e) {
			SysUtil.sleep(5);
			locate.click("搜索");
			locate.send("输入框", "皇后中式炒锅");
		}
		locate.click("搜索");
		locate.atScreen("搜索结果");
	}

}
