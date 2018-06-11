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
		SysUtil.sleep(10);
	}
	
	public void confirm() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
//		locate.click("去结算");
		try {
			androidDriver.findElementByXPath("//android.view.View[@content-desc='去结算']").click();
		} catch (Exception e) {
			SysUtil.sleep(10);
			int x = androidDriver.findElementByXPath("//android.view.View[@content-desc='去结算']").getLocation().getX();
			int y = androidDriver.findElementByXPath("//android.view.View[@content-desc='去结算']").getLocation().getY();
			androidDriver.tap(1, x, y, 1);
			SysUtil.sleep(10);
		}
		SysUtil.sleep(5);
		locate.clickIfItemDisplayed("确定");
		SysUtil.sleep(10);
		if (isConfirmStillDisplayed()) {
			int x = androidDriver.findElementById("cartpageLabelConfirmSingle").getLocation().getX();
			int y = androidDriver.findElementById("cartpageLabelConfirmSingle").getLocation().getY();
			androidDriver.tap(1, x, y, 1);
			SysUtil.sleep(15);
		}
	}
	
	private boolean isConfirmStillDisplayed() throws InterruptedException{
		try {
			return androidDriver.findElementById("cartpageLabelConfirmSingle").isDisplayed();
		} catch (Exception e) {
			return false;
		} 
	}

	public List<String> getCurrentShopper() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("当前购货人");
	}

	public String getGoodsAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("婴儿沐浴露数量");
	}
	
	public String getBV() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("净营业额");
	}
	
	public String getPV() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("销售指数");
	}

	public List<String> getMoneyAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("金额小计");
	}
	
	public List<String> getTotalMoneyAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("总额");
	}
	


}
