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
	
	public List<String> getMultipleValues() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("多个验证");
	}
	
	public List<String> getTotalMoneyAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		SysUtil.sleep(10);
		return validation.getAllValidationInfo("订单总金额");
	}

	public String getBuyAmount() throws InterruptedException {
//		Locate locate = new Locate(null, androidDriver);
//		locate.swipeAction("up");
//		locate.swipeAction("up");
//		locate.swipeAction("up");
		SysUtil.sleep(10);
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("购货总额");
	}
	
	public String getFreight() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("运费");
	}
	
	
	public String getDeductedPoints() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("扣减悦享分");
	}
	

}
