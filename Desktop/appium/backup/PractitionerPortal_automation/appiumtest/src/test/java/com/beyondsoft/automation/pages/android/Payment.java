package com.beyondsoft.automation.pages.android;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import com.beyondsoft.automation.basevalidation.Base;
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
		Base base = new Base(null, androidDriver);
		SysUtil.sleep(5);
		String amount;
		int retry = 5;
		for (int i = 0; i< retry; i++) {
			amount = base.getAllValidationInfo("总金额").toString();
			if (!amount.contains("总金额")) {
				SysUtil.sleep(5);
				continue;
			}
			String rep = "[^0-9]";
			Pattern p = Pattern.compile(rep); 
			Matcher m = p.matcher(amount);  
			  
			String string = m.replaceAll(" ").trim();
			String[] x = string.split(" ");
			System.out.println(x[0].trim());
			if (x[0].trim() != "") {
				return x[0].trim();
			}
			SysUtil.sleep(5);
		}
		return null;
	}


	public List<String> paymentValidation() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		return base.getAllValidationInfo("支付成功信息");
	}
	
	public List<String> paymentIncompleteValidation() throws InterruptedException {
		Base base = new Base(null, androidDriver);
		return base.getAllValidationInfo("支付未完成信息");
	}
	

}
