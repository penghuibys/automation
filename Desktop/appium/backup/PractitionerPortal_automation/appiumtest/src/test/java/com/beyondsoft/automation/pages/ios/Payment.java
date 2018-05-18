
package com.beyondsoft.automation.pages.ios;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.beyondsoft.automation.basevalidation.Base;
import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.util.SysUtil;


public class Payment {
	public Payment(AppiumDriver<WebElement> iOSDriver) {
		Payment.iOSDriver = iOSDriver;
	}

	public static AppiumDriver<WebElement> iOSDriver;
	
	public void goToPayment() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.click("去支付");
		locate.atScreen("提交成功");
	}
	
	public void doPayment() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.click("立即支付");
	}
	
	public void paymentSelection(String payment) throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.clickIfItemDisplayed(payment);
	}
	
	public void paymentConfirmation() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.click("确认付款");
	}
	
	public void paymentCompletion() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		List<WebElement> elements = locate.elements(iOSDriver, "密码输入框");
		if (elements.size() == 6) {
			elements.get(0).click();//打开键盘, workaround
			iOSDriver.getKeyboard().sendKeys("1");
			iOSDriver.getKeyboard().sendKeys("0");
			iOSDriver.getKeyboard().sendKeys("2");
			iOSDriver.getKeyboard().sendKeys("6");
			iOSDriver.getKeyboard().sendKeys("8");
			iOSDriver.getKeyboard().sendKeys("1");
		}
		SysUtil.sleep(5);
		locate.click("完成支付");
	}
	
	public List<String> exchangeValidation() throws InterruptedException {
		Base base = new Base(iOSDriver, null);
		return base.getAllValidationInfo("兑换成功信息");
	}
	
	public List<String> paymentValidation() throws InterruptedException {
		Base base = new Base(iOSDriver, null);
		return base.getAllValidationInfo("支付成功信息");
	}
	
	public List<String> paymentAmount() throws InterruptedException {
		Base base = new Base(iOSDriver, null);
		return base.getAllValidationInfo("购货总额");
	}


}
