package com.beyondsoft.automation.pages.android;

import java.util.List;
import java.util.Set;

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
	//	androidDriver.tap(1, 228, 1681, 1);
		androidDriver.tap(1, 220, 1680, 1);
		locate.send("手机号", "18086627859");
		locate.click("下一步");
		locate.send("密码", "test0517");
		locate.click("登录");
		locate.click("不检查通讯录");

		// androidDriver.findElementById("com.tencent.mm:id/ak_").click();
		// androidDriver.findElementByXPath("//android.widget.LinearLayout[@resource-id='com.tencent.mm:id/cb_']/android.widget.EditText").sendKeys("penghui19811026");
		// androidDriver.findElementById("com.tencent.mm:id/ak_").click();
		// androidDriver.findElementById("com.tencent.mm:id/alk").click();

		SysUtil.sleep(20);
		locate.click("搜索公众号");
		locate.send("输入框", "anlibaojie");

		locate.click("公众号链接");
		locate.click("线上工作室");
		locate.click("安利云购链接");
		SysUtil.sleep(15);

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
		SysUtil.sleep(5);
	}

	// 个人护理
	public void navigateToPersonalCare() throws InterruptedException {
		Locate locate = new Locate(null, androidDriver);
		locate.click("个人护理");
	}

}
