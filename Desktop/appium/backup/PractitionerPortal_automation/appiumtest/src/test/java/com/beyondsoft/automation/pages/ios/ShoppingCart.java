package com.beyondsoft.automation.pages.ios;

import java.util.List;
import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.base.Validation;

import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.util.SysUtil;
import io.appium.java_client.AppiumDriver;

public class ShoppingCart {
	public static AppiumDriver<WebElement> iOSDriver;

	public ShoppingCart(AppiumDriver<WebElement> iOSDriver) {
		ShoppingCart.iOSDriver = iOSDriver;
	}

	public void addToShoppingCart() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.click("加入购物车");
	}

	public void goToShoppingCart() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.click("前往购物车");
	}

	public void confirm() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.click("去结算");
		locate.click("活动信息确认");
	}

	public String getShoppingCount() throws InterruptedException {
		Validation Validation = new Validation(iOSDriver, null);
		return Validation.getValidationInfo("购物数量");
	}

	public String getCurrentBuyer() throws InterruptedException {
		Validation validation = new Validation(iOSDriver, null);
		SysUtil.sleep(15);
		String buyer = validation.getValidationInfo("当前购货人").toString();
		System.out.println(buyer);
		return buyer;
	}

	public List<String> getGoodsAmount() throws InterruptedException {
		Validation validation = new Validation(iOSDriver, null);
		return validation.getAllValidationInfo("商品总数");
	}

	public List<String> getMoneyAmount() throws InterruptedException {
		Validation validation = new Validation(iOSDriver, null);
		SysUtil.sleep(15);
		return validation.getAllValidationInfo("总金额");
	}

	public List<String> getPromotionGift() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.swipeAction("up");
		Validation validation = new Validation(iOSDriver, null);
		return validation.getAllValidationInfo("促销赠品");
	}

}
