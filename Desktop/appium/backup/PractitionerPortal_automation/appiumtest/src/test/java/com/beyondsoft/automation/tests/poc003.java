
package com.beyondsoft.automation.tests;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import frame.com.mtf.ibm.init.IOSInit;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.base.TestBase;

import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import com.beyondsoft.automation.pages.Login;
import com.beyondsoft.automation.pages.MainPage;
import com.beyondsoft.automation.pages.RichJayCoffee;
import com.beyondsoft.automation.pages.Exchange;


public class poc003 extends TestBase{
	
	public String username;
	public String password;
	public IOSDriver<WebElement> iosDriver;
	public IOSInit ios;
    public WebDriver mobileDriver = null;

  
  @BeforeTest
  public void setUp() throws MalformedURLException{
	  	ios = new IOSInit();
	  	
	  	iosDriver = ios.launchRealApp();
	  	setMobileDriver(iosDriver); 
	  	
		data.loadData("testdata.xlsx", "mobile");
		username = data.getData("mobileTest", "username");
		password = data.getData("mobileTest", "password");
  }


	
  @Test
  public void mobileTestDemo() throws InterruptedException, MalformedURLException {
	  Login loign = new Login(iosDriver);
	  loign.signIn("", "");
	  
	  MainPage mainPage = new MainPage(iosDriver);
	  mainPage.navigateToAmywayCloudShopping();
	  mainPage.searchRichJayCoffee();
	  
	  RichJayCoffee richJayCoffee = new RichJayCoffee(iosDriver);
	  richJayCoffee.setExchange();
	  
	  Exchange exchange = new Exchange(iosDriver);
	  exchange.confirmExchange();
	  List<String> exchangeCount = exchange.getExchangeCount("兑换数量");
	  FrameAssertion.contains(exchangeCount.toString(), "2", "验证兑换数量");
	  exchange.submitExchange();
	  
	  List<String> moneyToPay = exchange.getMoneyToPay("应付总金额");
	  List<String> bonuspointsToDeduct = exchange.getBonuspointsToDeduct("扣减悦享分");
	  FrameAssertion.contains(moneyToPay.toString(), "￥ 20.00", "验证应付总金额");
	  FrameAssertion.contains(bonuspointsToDeduct.toString(), "20.0", "扣减悦享分");
	  
  }
  
}











