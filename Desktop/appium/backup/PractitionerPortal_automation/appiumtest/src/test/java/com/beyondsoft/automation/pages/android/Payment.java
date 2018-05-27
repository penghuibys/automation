package com.beyondsoft.automation.pages.android;



import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		SysUtil.sleep(5); 
		locate.atScreen("提交成功");
		SysUtil.sleep(5); 
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
	//	locate.swipeAction("up");
	//	locate.clickIfItemDisplayed(payment);
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
	}

	public String getTotalMoneyAmount() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		SysUtil.sleep(5);
		
		String amount = validation.getValidationInfo("总金额").toString();
//		String amount;
//		int retry = 5;
//		for (int i = 0; i< retry; i++) {
//			amount = validation.getValidationInfo("总金额").toString();
//			if (!amount.contains("总金额")) {
//				SysUtil.sleep(5);
//				continue;
//			}
//			String rep = "[^0-9]";
//			Pattern p = Pattern.compile(rep); 
//			Matcher m = p.matcher(amount);  
//			  
//			String string = m.replaceAll(" ").trim();
//			String[] x = string.split(" ");
//			System.out.println(x[0].trim());
//			if (x[0].trim() != "") {
//				return x[0].trim();
//			}
//			SysUtil.sleep(5);
//		}
		return amount;
	}


	public List<String> paymentValidation() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("支付成功信息");
	}
	
	public List<String> paymentIncompleteValidation() throws InterruptedException {
		Validation validation = new Validation(null, androidDriver);
		return validation.getAllValidationInfo("支付未完成信息");
	}
	

}
