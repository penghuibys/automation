package com.beyondsoft.automation.pages.android;

import java.util.List;
import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.base.Validation;

import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.util.SysUtil;
import io.appium.java_client.AppiumDriver;

public class ShoppingCart {
	public static AppiumDriver<WebElement> androidDriver;

	public ShoppingCart(AppiumDriver<WebElement> androidDriver) {
		ShoppingCart.androidDriver = androidDriver;
	}

	public void addToShoppingCart() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("加入购物车");
		SysUtil.sleep(10);
	}

	public void goToShoppingCart() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("前往购物车");
	}
	
	public void confirm() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("去结算");
	}

	public List<String> getCurrentBuyer() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("当前购货人");
	}

	public String getGoodsAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("商品总数");
	}

	public List<String> getMoneyAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("金额小计");
	}



}
