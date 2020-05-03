package com.beyondsoft.automation.pages.android;


import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.base.TestBase;
import frame.com.pp.auto.util.SysUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class Video extends TestBase {

	public static AppiumDriver<WebElement> androidDriver;

	public Video(AppiumDriver<WebElement> androidDriver) {
		Video.androidDriver = androidDriver;
	}

	public void demo() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		SysUtil.sleep(10);
		for (int i = 0; i <= 10000; i++) {
			locate.swipeAction("up");
			SysUtil.sleep(5);
		}
	}





}
