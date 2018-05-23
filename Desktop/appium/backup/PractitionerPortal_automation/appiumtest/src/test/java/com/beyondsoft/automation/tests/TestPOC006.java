
package com.beyondsoft.automation.tests;

import org.testng.annotations.Test;
import frame.com.pp.auto.action.FrameAssertion;
import java.net.MalformedURLException;
import java.util.List;
import com.beyondsoft.automation.pages.android.MainPage;
import com.beyondsoft.automation.pages.android.Payment;
import com.beyondsoft.automation.pages.android.PersonalCare;
import com.beyondsoft.automation.pages.android.ShoppingCart;
import com.beyondsoft.automation.pages.android.Settlement;
import com.beyondsoft.automation.tests.base.Base;


public class TestPOC006 extends Base{
	
	
  @Test
  public void mobileTestPOC005() throws InterruptedException, MalformedURLException {
	  setUpAndroidDriver();
	  setUpUser();
	  
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
	  FrameAssertion.contains(totalMoneyAmount_order.toString(), totalMoneyAmount.toString().split("订单金额:")[1].trim(), "验证应付总金额");
	  
	  payment.paymentSelection("微信支付");
	  payment.doPayment();
	  payment.cancelPayment();
	  List<String> validation = payment.paymentIncompleteValidation();
	  System.out.println(validation.toString());
	  FrameAssertion.contains(validation.toString(), "您的支付未完成", "验证支付成功信息");
	  
  }
  
}











