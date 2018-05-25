
package com.beyondsoft.automation.tests;

import org.testng.annotations.Test;
import frame.com.pp.auto.action.FrameAssertion;
import java.net.MalformedURLException;
import java.util.List;
import com.beyondsoft.automation.pages.ios.Exchange;
import com.beyondsoft.automation.pages.ios.Login;
import com.beyondsoft.automation.pages.ios.MainPage;
import com.beyondsoft.automation.pages.ios.Payment;
import com.beyondsoft.automation.pages.ios.RichJayCoffee;
import com.beyondsoft.automation.tests.base.Base;


public class TestPOC003 extends Base{

	
  @Test
  public void mobileTestPOC003() throws InterruptedException, MalformedURLException {
	  setUpIosDriver();
	  setUpUser();
	  
	  Login loign = new Login(iosDriver);
	  loign.signIn(amwayId, password);
	  
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
	  
	  Payment payment = new Payment(iosDriver);
	  payment.doPayment();
	  payment.paymentSelection("支付宝");
	  payment.paymentConfirmation();
	  payment.paymentCompletion();
	  List<String> validation = payment.paymentValidation();
	  FrameAssertion.contains(validation.toString(), "成功支付", "验证支付成功信息");
//	  FrameAssertion.contains(validation.toString(), "RICH & JAY电子咖啡券 2 张", "验证支付成功信息");
//	  FrameAssertion.contains(validation.toString(), "成功扣除", "验证支付成功信息");
//	  FrameAssertion.contains(validation.toString(), "20.0 悦享分", "验证支付成功信息");
	  
  }
  
}











