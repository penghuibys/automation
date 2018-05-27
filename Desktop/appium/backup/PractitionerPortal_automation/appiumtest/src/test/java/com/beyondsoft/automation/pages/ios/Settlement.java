
package com.beyondsoft.automation.pages.ios;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

import com.beyondsoft.automation.base.Validation;

import frame.com.mtf.ibm.operation.Locate;

public class Settlement {
	public Settlement(AppiumDriver<WebElement> iOSDriver) {
		Settlement.iOSDriver = iOSDriver;
	}

	public static AppiumDriver<WebElement> iOSDriver;
	
	public void requireInvoice() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
	//	WebElement element = locate.element(iOSDriver, "需要发票");
		locate.swipeAction("up");
		try {
			locate.click("需要发票");
		} catch (Exception e) {
			locate.swipeAction("up");
			locate.click("需要发票");
		}
	}
	
	public void selectVATSpecialInvoice() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		try {
			locate.click("发票类型");
		} catch (Exception e) {
			locate.swipeAction("up");
			locate.click("发票类型");
		}
		locate.click("查看全部发票");
		locate.click("选择发票");
		locate.click("发票确认");
	}


	public String getDefaultDelivery() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.atScreen("配送方式");
		
		Validation validation = new Validation(iOSDriver, null);
		return validation.getValidationValue("默认配送方式");
	}
	
	public List<String> getTotalAmount() throws InterruptedException {
		Locate locate = new Locate(iOSDriver, null);
		locate.swipeAction("up");
		
		Validation validation = new Validation(iOSDriver, null);
		return validation.getAllValidationInfo("购货总额");
	}

}
