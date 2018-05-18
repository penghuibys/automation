package com.beyondsoft.automation.pages.android;

import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import io.appium.java_client.AppiumDriver;


public class PersonalCare {
	
	public static AppiumDriver<WebElement> androidDriver;
	
	public PersonalCare(AppiumDriver<WebElement> androidDriver){
		PersonalCare.androidDriver = androidDriver;
	}

	//添加婴儿沐浴露
	public void addBabyShampoo() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("搜索");
		locate.send("商品输入框", "39597");
		locate.click("搜索");
		
	//	locate.send("数量", "2");
		locate.click("添加");
	}
	

}
