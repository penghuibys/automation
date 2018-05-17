
package com.beyondsoft.automation.pages;

import io.appium.java_client.AppiumDriver;

import java.util.List;

import org.openqa.selenium.WebElement;

import frame.com.mtf.ibm.operation.Locate;
import com.beyondsoft.automation.pages.Base;

public class Pay {
	public Pay(AppiumDriver<WebElement> iOSDriver) {
		Pay.iOSDriver = iOSDriver;
	}

	public static AppiumDriver<WebElement> iOSDriver;
	
	public void requireInvoice() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.swipeAction("up");
		locate.click("需要发票");
	}
	
	public void selectVATSpecialInvoice() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.click("发票类型");
		locate.click("查看全部发票");
		locate.click("选择发票");
		locate.click("发票确认");
	}


	public String getDefaultDelivery() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.atScreen("配送方式");
		
		Base base = new Base(iOSDriver);
		return base.getValidationValue("默认配送方式");
	}
	
	public List<String> getTotalAmount() throws InterruptedException {
		Locate locate = new Locate(iOSDriver);
		locate.swipeAction("up");
		
		Base base = new Base(iOSDriver);
		return base.getAllValidationInfo("购货总额");
	}

}
