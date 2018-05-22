package com.beyondsoft.automation.pages.android;

import java.util.List;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import com.beyondsoft.automation.basevalidation.Base;

import frame.com.pp.auto.util.SysUtil;

public class Settlement {
	public static AppiumDriver<WebElement> androidDriver;

	public Settlement(AppiumDriver<WebElement> androidDriver) {
		Settlement.androidDriver = androidDriver;
	}

	public String getGoodsAmount() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		return base.getValidationInfo("商品总数");
	}

	public List<String> getMoneyAmount() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		return base.getAllValidationInfo("金额小计");
	}
	
	public List<String> getTotalMoneyAmount() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		SysUtil.sleep(6);
		return base.getAllValidationInfo("订单总金额");
	}



}
