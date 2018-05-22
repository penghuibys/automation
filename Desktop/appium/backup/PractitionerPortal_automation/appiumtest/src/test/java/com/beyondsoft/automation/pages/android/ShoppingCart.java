package com.beyondsoft.automation.pages.android;

import java.util.List;
import org.openqa.selenium.WebElement;
import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.util.SysUtil;
import io.appium.java_client.AppiumDriver;
import com.beyondsoft.automation.basevalidation.Base;

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
		Base base = new Base(null, androidDriver);
		return base.getAllValidationInfo("当前购货人");
	}

	public String getGoodsAmount() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		return base.getValidationInfo("商品总数");
	}

	public List<String> getMoneyAmount() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		return base.getAllValidationInfo("金额小计");
	}



}
