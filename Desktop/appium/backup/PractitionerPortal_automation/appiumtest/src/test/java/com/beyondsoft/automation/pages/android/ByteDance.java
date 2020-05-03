package com.beyondsoft.automation.pages.android;


import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.base.TestBase;
import frame.com.pp.auto.util.SysUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ByteDance extends TestBase {

	public static AppiumDriver<WebElement> androidDriver;

	public ByteDance(AppiumDriver<WebElement> androidDriver) {
		ByteDance.androidDriver = androidDriver;
	}

	public void demo() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		SysUtil.sleep(5);
		for (int i = 0; i <= 10; i++) {
			locate.click("task");
			SysUtil.sleep(8);
			locate.click("box");
			SysUtil.sleep(8);
//			locate.click("openVideo");
			androidDriver.tap(1, 520, 1220, 1);
			SysUtil.sleep(25);
			androidDriver.tap(1, 915, 121, 1);
			SysUtil.sleep(10);
			Date date = new Date();
			long interval;
			do {
				SysUtil.sleep(10);
				interval = (new Date().getTime() - date.getTime())/1000;
				System.out.println("Already wait for " + interval+ "s");
			} while (interval < 600);

		}
	}





}
