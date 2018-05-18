package com.beyondsoft.automation.pages.android;


import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.base.TestBase;
import io.appium.java_client.AppiumDriver;
import frame.com.pp.auto.util.SysUtil;


public class MainPage extends TestBase{
	
	public static AppiumDriver<WebElement> androidDriver;
	
	public MainPage(AppiumDriver<WebElement> androidDriver){
		MainPage.androidDriver = androidDriver;
	}
	
	public void navigateToAmywayCloudShopping() throws InterruptedException {
		  Locate locate = new Locate(null, androidDriver);
		  locate.click("搜索公众号");
		  locate.send("输入框", "安利保洁用品");
		  
		  locate.click("公众号链接");
		  locate.click("线上工作室");
		  locate.click("安利云购链接");
		  SysUtil.sleep(3);
		  locate.clickIfItemDisplayed("我知道了");
	}

	//个人护理
	public void navigateToPersonalCare() throws InterruptedException {
		  Locate locate = new Locate(null, androidDriver);
		  locate.click("个人护理");
	}

	//添加婴儿沐浴露
	public void addBabyShampoo() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("搜索");
		locate.send("商品输入框", "39597");
		locate.click("搜索");

		locate.click("婴儿沐浴露");
		locate.swipeAction("up");
		locate.send("数量", "2");
		locate.click("加入购物车");
	}
	
	public void searchHuangHouChaoGuo() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("搜索");
		locate.send("输入框", "皇后中式炒锅");
		locate.click("搜索");
		locate.atScreen("搜索结果");
	}

}
