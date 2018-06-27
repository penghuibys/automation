package com.beyondsoft.automation.pages.android;



import java.util.List;

import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.base.Validation;

import io.appium.java_client.AppiumDriver;
import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.util.SysUtil;

public class Order {
	public static AppiumDriver<WebElement> androidDriver;

	public Order(AppiumDriver<WebElement> androidDriver) {
		Order.androidDriver = androidDriver;
	}
	
	public void goToOrderDetails() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("查看详情");
		SysUtil.sleep(10); 
		// bug: always pop up once the page refreshes
		locate.clickIfItemDisplayed("我知道了1");
		locate.clickIfItemDisplayed("我知道了");
		SysUtil.sleep(2);
	}
	
	public void doPayment() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
	//	locate.swipeAction("down");
		
		SysUtil.sleep(5); 
		
		try {
			androidDriver.findElementByAccessibilityId("立即支付").click();
			SysUtil.sleep(5); 
		} catch (Exception e) {
			SysUtil.sleep(10);
			int x = androidDriver.findElementByXPath("//android.widget.Button[@content-desc='立即支付']").getLocation().getX();
			int y = androidDriver.findElementByXPath("//android.widget.Button[@content-desc='立即支付']").getLocation().getY();
			androidDriver.tap(1, x, y, 1);
			SysUtil.sleep(10);
		}
		// bug: always pop up once the page refreshes
//		locate.clickIfItemDisplayed("我知道了1");
//		locate.clickIfItemDisplayed("我知道了");
	}
	
	public String getPayed() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.atScreen("付款信息");

		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("已支付金额");
	}
	
	public String getToPay() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("剩余应付");
	}
	
	public String getOrderOperator() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("订单操作人");
	}
	
	public String getChannel() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("购货渠道");
	}
	
	public String getOrderStatus() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("订单状态");
	}
	
	public String getPaymentInformation() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getValidationInfo("付款信息");
	}
	
	public List<String> getMultiValidation() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.swipeAction("up");
		
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("多种金额");
	}


}
