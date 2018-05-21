
package com.beyondsoft.automation.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import frame.com.mtf.ibm.init.IOSInit;
import frame.com.mtf.ibm.init.AndroidInit;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.base.TestBase;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.WebDriver;

import com.beyondsoft.automation.model.userInfo;
import com.beyondsoft.automation.pages.android.MainPage;
import com.beyondsoft.automation.pages.android.PersonalCare;
import com.beyondsoft.automation.pages.android.ShoppingCart;
import com.beyondsoft.automation.pages.android.Settlement;



import mobile.appiumtest.Utilities;
import frame.com.mtf.ibm.operation.Locate;


public class POC005 extends TestBase{
	
	public String amwayId;
	public String password;
	public AndroidDriver<WebElement> androidDriver;
	public IOSInit ios;
	public AndroidInit android;
    public WebDriver mobileDriver = null;

  
  @BeforeTest
  public void setUp() throws MalformedURLException{
	  	android = new AndroidInit();
	  	androidDriver = android.launchApp();

	  	setMobileDriver(androidDriver); 
  }
	
//  @Test
  public void mobileTestPOC005() throws InterruptedException, MalformedURLException {
	  MainPage mainPage = new MainPage(androidDriver);
	  mainPage.navigateToAmywayCloudShopping();
	  
	  PersonalCare personCare = new PersonalCare(androidDriver);
	  personCare.addBabyShampoo();
	  
	  ShoppingCart shoppingCart = new ShoppingCart(androidDriver);
	  shoppingCart.addToShoppingCart();
	  shoppingCart.goToShoppingCart();
	  

	  List<String> getCurrentBuyer = shoppingCart.getCurrentBuyer();
	  FrameAssertion.contains(getCurrentBuyer.toString(), amwayId, "验证当前购货人");
	  
	  List<String> goodsAmount = shoppingCart.getGoodsAmount();
	  FrameAssertion.contains(goodsAmount.toString(), "2", "验证商品总数");
	  
	  List<String> moneyAmount = shoppingCart.getMoneyAmount();
	  FrameAssertion.contains(moneyAmount.toString(), "320", "验证总金额");
	  
	  shoppingCart.confirm();
	  
	  Settlement settlement = new Settlement(androidDriver);
	  goodsAmount = settlement.getGoodsAmount();
	  FrameAssertion.contains(goodsAmount.toString(), "2", "验证商品总数");
	  
	  moneyAmount = settlement.getMoneyAmount();
	  FrameAssertion.contains(moneyAmount.toString(), "320", "验证总金额");
	  
  }
  
}











