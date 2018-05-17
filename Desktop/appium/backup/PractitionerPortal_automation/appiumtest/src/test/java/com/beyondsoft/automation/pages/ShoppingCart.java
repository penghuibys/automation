package com.beyondsoft.automation.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import frame.com.mtf.ibm.operation.Locate;
import io.appium.java_client.AppiumDriver;

public class ShoppingCart {
	public static AppiumDriver<WebElement> iOSDriver;

	public ShoppingCart(AppiumDriver<WebElement> iOSDriver) {
		ShoppingCart.iOSDriver = iOSDriver;
	}

	public void addToShoppingCart() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.click("加入购物车");
	}

	public void goToShoppingCart() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.click("前往购物车");
	}

	public void confirm() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.click("去结算");
		locate.click("活动信息确认");
	}

	public String getShoppingCount() throws InterruptedException {
		Base base = new Base(iOSDriver);
		return base.getValidationInfo("购物数量");
	}

	public List<String> getCurrentBuyer() throws InterruptedException {
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo("当前购货人");
	}

	public List<String> getGoodsAmount() throws InterruptedException {
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo("商品总数");
	}

	public List<String> getMoneyAmount() throws InterruptedException {
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo("总金额");
	}

	public List<String> getPromotionGift() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.swipeAction("up");
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo("促销赠品");
	}

}
