package com.beyondsoft.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import io.appium.java_client.AppiumDriver;

public class Exchange {
	
	public static AppiumDriver<WebElement> iOSDriver;
	
	public Exchange(AppiumDriver<WebElement> iOSDriver){
		Exchange.iOSDriver = iOSDriver;
	}
	
	
	public void confirmExchange () throws InterruptedException {
		  Locate locate = new Locate(iOSDriver);
		  locate.click("立即兑换");
	}
	
	public void submitExchange () throws InterruptedException {
		  Locate locate = new Locate(iOSDriver);
		  locate.send("输入手机号", "13434122152");
		  locate.send("确认手机号", "13434122152");
		  locate.click("兑换数量"); //workaround to dismiss keyboard
		  locate.click("确定");
		  locate.atScreen("提交成功");
	}
	
	public List<String> getExchangeCount(String target) throws InterruptedException {
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo(target);
	}
	
	public List<String> getMoneyToPay(String target) throws InterruptedException {
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo(target);
	}

	public List<String> getBonuspointsToDeduct(String target) throws InterruptedException {
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo(target);
	}


}
