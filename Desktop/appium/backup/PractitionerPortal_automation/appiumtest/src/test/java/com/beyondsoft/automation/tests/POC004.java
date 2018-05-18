
package com.beyondsoft.automation.tests;


import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import frame.com.mtf.ibm.init.IOSInit;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.base.TestBase;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.beyondsoft.automation.model.userInfo;
import com.beyondsoft.automation.pages.ios.HuangHouChaoGuo;
import com.beyondsoft.automation.pages.ios.Login;
import com.beyondsoft.automation.pages.ios.MainPage;
import com.beyondsoft.automation.pages.ios.PrePay;
import com.beyondsoft.automation.pages.ios.ShoppingCart;

import mobile.appiumtest.Utilities;


public class POC004 extends TestBase{
	
	public String amwayId;
	public String password;
	public IOSDriver<WebElement> iosDriver;
    public WebDriver mobileDriver = null;

  
  @BeforeTest
  public void setUp() throws MalformedURLException{
	  	IOSInit ios = new IOSInit();
	  	iosDriver = ios.launchRealApp();
	  	setMobileDriver(iosDriver); 
		File app = new File("test-data/" + "user.json");
	  	userInfo user = Utilities.load(app.getAbsolutePath(), "user", userInfo.class);
		amwayId = user.getAmwayId();
		password = user.getPassword();
  }


	
  @Test
  public void mobileTestPOC004() throws InterruptedException, MalformedURLException {
	  Login loign = new Login(iosDriver);
	  loign.signIn(amwayId, password);
	  
	  MainPage mainPage = new MainPage(iosDriver);
	  mainPage.navigateToAmywayCloudShopping();
	  mainPage.searchHuangHouChaoGuo();
 
	  HuangHouChaoGuo chaoGuo = new HuangHouChaoGuo(iosDriver);
	  chaoGuo.addToShoppingCart();
	  ShoppingCart shoppingCart = new ShoppingCart(iosDriver);
	  String shoppingCount = shoppingCart.getShoppingCount();
	  FrameAssertion.contains(shoppingCount, "1", "验证加入购物车数量");
	  
	  shoppingCart.goToShoppingCart();
	  List<String> goodsAmount = shoppingCart.getGoodsAmount();
	  FrameAssertion.contains(goodsAmount.toString(), "4", "验证商品总数");
	  
	  List<String> getCurrentBuyer = shoppingCart.getCurrentBuyer();
	  FrameAssertion.contains(getCurrentBuyer.toString(), amwayId, "验证当前购货人");
	  
	  List<String> moneyAmount = shoppingCart.getMoneyAmount();
	  FrameAssertion.contains(moneyAmount.toString(), "2,500", "验证总金额");//
	  
	  List<String> promotionGifts = shoppingCart.getPromotionGift();
	  FrameAssertion.equals(promotionGifts.size(), 3, "验证附件个数");
	  
	  shoppingCart.confirm();
	  
	  PrePay prePay = new PrePay(iosDriver);
	  String defaultDelivery = prePay.getDefaultDelivery();
	  FrameAssertion.equals(defaultDelivery, "1", "默认配送方式");//默认配送方式周一至周日：选中为1, 未选则为0
	  
	  prePay.requireInvoice();
	  prePay.selectVATSpecialInvoice();
	  List<String> totalAmount = prePay.getTotalAmount();
	  FrameAssertion.contains(totalAmount.toString(), "2,500", "验证总金额");

  }
  
}











