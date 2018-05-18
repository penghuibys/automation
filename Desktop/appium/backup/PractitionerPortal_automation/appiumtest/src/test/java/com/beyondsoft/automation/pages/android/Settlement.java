package com.beyondsoft.automation.pages.android;

import java.util.List;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import com.beyondsoft.automation.basevalidation.Base;

public class Settlement {
	public static AppiumDriver<WebElement> androidDriver;

	public Settlement(AppiumDriver<WebElement> androidDriver) {
		Settlement.androidDriver = androidDriver;
	}

	public List<String> getGoodsAmount() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		return base.getAllValidationInfo("商品总数");
	}

	public List<String> getMoneyAmount() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		return base.getAllValidationInfo("总金额");
	}



}
