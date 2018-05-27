package com.beyondsoft.automation.pages.android;

import java.util.List;
import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.base.Validation;

import io.appium.java_client.AppiumDriver;
import frame.com.pp.auto.util.SysUtil;

public class Settlement {
	public static AppiumDriver<WebElement> androidDriver;

	public Settlement(AppiumDriver<WebElement> androidDriver) {
		Settlement.androidDriver = androidDriver;
	}

	public String getGoodsAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("商品总数");
	}

	public List<String> getMoneyAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("金额小计");
	}
	
	public List<String> getTotalMoneyAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		SysUtil.sleep(6);
		return validation.getAllValidationInfo("订单总金额");
	}



}
