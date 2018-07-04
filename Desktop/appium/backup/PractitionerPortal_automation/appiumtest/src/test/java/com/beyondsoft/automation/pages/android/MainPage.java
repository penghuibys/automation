package com.beyondsoft.automation.pages.android;


import org.openqa.selenium.WebElement;
import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.base.TestBase;
import io.appium.java_client.AppiumDriver;
import frame.com.pp.auto.util.SysUtil;

public class MainPage extends TestBase {

	public static AppiumDriver<WebElement> androidDriver;

	public MainPage(AppiumDriver<WebElement> androidDriver) {
		MainPage.androidDriver = androidDriver;
	}

	public void navigateToAmywayCloudShopping() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		SysUtil.sleep(5);
		locate.click("登录微信");
		locate.send("手机号", "18086627859");
		locate.click("下一步");
		locate.send("密码", "test0517");
		locate.click("登录");
		locate.click("不检查通讯录");

		SysUtil.sleep(20);
		locate.click("搜索公众号");
		locate.send("输入框", "anlibaojie");

		locate.click("公众号链接");
		SysUtil.sleep(2);
		locate.click("线上工作室");
		SysUtil.sleep(2);
		locate.click("安利云购链接");
		SysUtil.sleep(25);

		// Set<String> contexts = androidDriver.getContextHandles();
		// System.out.println(contexts);
		// androidDriver.context("WEBVIEW_com.tencent.mm:tools");
		// System.out.println(androidDriver.getContext());
		// List<WebElement> ele =
		// androidDriver.findElementsByXPath(".//section[contains(@class,'notification-banner-two')]//div[@class='ball']");
		// System.out.println(ele.size());
		//
		// WebElement elem =
		// androidDriver.findElementByXPath(".//div[@class='mobile-navigation-detail']");
		// System.out.println(elem.getAttribute("class"));

		// bug: always pop up once the page refreshes
		locate.clickIfItemDisplayed("我知道了1");
		locate.clickIfItemDisplayed("我知道了");
		SysUtil.sleep(2);
	}

	// 个人护理
	public void navigateToPersonalCare() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("个人护理");
	}

}
