package com.beyondsoft.automation.pages.android;



import java.util.List;

import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.base.Validation;

import io.appium.java_client.AppiumDriver;
import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.util.SysUtil;

public class Payment {
	public static AppiumDriver<WebElement> androidDriver;

	public Payment(AppiumDriver<WebElement> androidDriver) {
		Payment.androidDriver = androidDriver;
	}
	
	public void goToPayment() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("去支付");
		SysUtil.sleep(10); 
		if (isGoPaymentStillDisplayed()) {
			int x = androidDriver.findElementByXPath("//android.widget.Button[@content-desc='去支付']").getLocation().getX();
			int y = androidDriver.findElementByXPath("//android.widget.Button[@content-desc='去支付']").getLocation().getY();
			androidDriver.tap(1, x, y, 1);
			SysUtil.sleep(5); 
		}
		
		locate.atScreen("提交成功");
		SysUtil.sleep(5); 
	}
	
	private boolean isGoPaymentStillDisplayed() throws InterruptedException{
		try {
			return androidDriver.findElementByXPath("//android.widget.Button[@content-desc='去支付']").isDisplayed();
		} catch (Exception e) {
			return false;
		} 
	}
	
	public void multiPayment(String amount) throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.swipeAction("up");
		SysUtil.sleep(5);
		locate.click("多笔支付");
		locate.send("本次支付金额", amount);
	}
	
	public void doPayment() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("立即支付");
	}
	
	public void cancelPayment() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("取消支付");
		SysUtil.sleep(5); 
	}
	
	public void paymentSelection(String payment) throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.swipeAction("up");
		SysUtil.sleep(2);
		locate.click(payment);
	}
	
	public void paymentCompletion() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		
		try {
			locate.send("密码输入框", "180517");
		} catch (Exception e) {
			locate.click("密码输入框");
			androidDriver.getKeyboard().sendKeys("1");
			androidDriver.getKeyboard().sendKeys("8");
			androidDriver.getKeyboard().sendKeys("0");
			androidDriver.getKeyboard().sendKeys("5");
			androidDriver.getKeyboard().sendKeys("1");
			androidDriver.getKeyboard().sendKeys("7");
		}

		locate.click("完成支付");
		SysUtil.sleep(10);
	}




	public List<String> paymentValidation() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		SysUtil.sleep(10);
		return validation.getAllValidationInfo("支付成功信息");
	}
	
	public List<String> paymentIncompleteValidation() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		SysUtil.sleep(5);
		return validation.getAllValidationInfo("支付未完成信息");
	}
	
	
	

}
