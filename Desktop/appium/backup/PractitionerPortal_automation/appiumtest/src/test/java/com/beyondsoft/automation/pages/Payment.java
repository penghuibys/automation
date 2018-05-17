
package com.beyondsoft.automation.pages;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import com.beyondsoft.automation.pages.Base;


public class Payment {
	public Payment(AppiumDriver<WebElement> iOSDriver) {
		Payment.iOSDriver = iOSDriver;
	}

	public static AppiumDriver<WebElement> iOSDriver;
	
	public void goToPayment() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.click("立即支付");
	}
	
	public void paymentSelection(String payment) throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.clickIfItemDisplayed(payment);
	}
	
	public void paymentConfirmation() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.click("确认付款");
	}
	
	public void paymentCompletion() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		List<WebElement> elements = locate.elements(iOSDriver, "密码输入框");
		if (elements.size() == 6) {
			elements.get(0).click();//打开键盘, workaround
			iOSDriver.getKeyboard().sendKeys("1");
			iOSDriver.getKeyboard().sendKeys("1");
			iOSDriver.getKeyboard().sendKeys("1");
			iOSDriver.getKeyboard().sendKeys("1");
			iOSDriver.getKeyboard().sendKeys("1");
			iOSDriver.getKeyboard().sendKeys("1");
		}
		locate.click("完成");
	}
	
	public List<String> paymentValidation() throws InterruptedException {
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo("兑换成功信息");
	}



}
