
package com.beyondsoft.automation.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import frame.com.mtf.ibm.init.AndroidInit;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.base.TestBase;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.WebDriver;

import com.beyondsoft.automation.model.userInfo;
import com.beyondsoft.automation.pages.android.MainPage;
import com.beyondsoft.automation.pages.android.Payment;
import com.beyondsoft.automation.pages.android.PersonalCare;
import com.beyondsoft.automation.pages.android.ShoppingCart;
import com.beyondsoft.automation.pages.android.Settlement;



import mobile.appiumtest.Utilities;


public class TestPOC005 extends TestBase{
	
	public String amwayId;
	public String password;
	public AndroidDriver<WebElement> androidDriver;
	public AndroidInit android;
    public WebDriver mobileDriver = null;

  
  @BeforeTest
  public void setUp() throws MalformedURLException{
	  	android = new AndroidInit();
	  	androidDriver = android.launchApp();

	  	setMobileDriver(androidDriver); 
		File app = new File("test-data/" + "user.json");
	  	userInfo user = Utilities.load(app.getAbsolutePath(), "user", userInfo.class);
		amwayId = user.getAmwayId();
  }
	
//  @Test
  public void mobileTestPOC005() throws InterruptedException, MalformedURLException {
	  MainPage mainPage = new MainPage(androidDriver);
	  mainPage.navigateToAmywayCloudShopping();
	  
	  PersonalCare personCare = new PersonalCare(androidDriver);
	  personCare.searchBabyShampoo();
	  personCare.setQuantity();
	  
	  ShoppingCart shoppingCart = new ShoppingCart(androidDriver);
	  shoppingCart.addToShoppingCart();
	  shoppingCart.goToShoppingCart();
	  
	  //购物车
	  List<String> getCurrentBuyer = shoppingCart.getCurrentBuyer();
	  FrameAssertion.contains(getCurrentBuyer.toString(), amwayId, "验证当前购货人");
	  String goodsAmount = shoppingCart.getGoodsAmount();
	  FrameAssertion.contains(goodsAmount, "2", "验证商品总数");
	  List<String> moneyAmount = shoppingCart.getMoneyAmount();
	  FrameAssertion.contains(moneyAmount.toString(), "320", "验证总金额");
	  
	  //结算
	  shoppingCart.confirm();
	  Settlement settlement = new Settlement(androidDriver);
	  List<String> totalMoneyAmount = settlement.getTotalMoneyAmount();
	  
	  //支付
	  Payment payment = new Payment(androidDriver);
	  payment.goToPayment();
	  List<String> totalMoneyAmount_order = payment.getTotalMoneyAmount();
	  System.out.println(totalMoneyAmount_order.toString());
	  FrameAssertion.equals(totalMoneyAmount_order.toString(), totalMoneyAmount.toString().split("订单金额:")[1].trim(), "验证应付总金额");
	  
	  payment.paymentSelection("微信支付");
	  payment.doPayment();
	  payment.paymentCompletion();
	  List<String> validation = payment.paymentValidation();
	  List<String> amount =  payment.getTotalMoneyAmount();
	  System.out.println(amount.toString());
	  FrameAssertion.contains(validation.toString(), "您已成功支付", "验证支付成功信息");
	  FrameAssertion.equals(totalMoneyAmount_order.toString(), amount.toString(), "验证应付总金额");

	  
  }
  
}










