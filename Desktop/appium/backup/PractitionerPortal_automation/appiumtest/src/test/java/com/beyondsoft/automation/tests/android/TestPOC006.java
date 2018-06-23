
package com.beyondsoft.automation.tests.android;

import org.testng.annotations.Test;
import frame.com.pp.auto.action.FrameAssertion;
import java.net.MalformedURLException;
import java.util.List;
import com.beyondsoft.automation.pages.android.MainPage;
import com.beyondsoft.automation.pages.android.Payment;
import com.beyondsoft.automation.pages.android.PersonalCare;
import com.beyondsoft.automation.pages.android.ShoppingCart;
import com.beyondsoft.automation.pages.android.Settlement;
import com.beyondsoft.automation.base.Setup;


public class TestPOC006 extends Setup{
	
	
  @Test
  public void mobileTestPOC006() throws InterruptedException, MalformedURLException {
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
	  List<String> getCurrentBuyer = shoppingCart.getCurrentShopper();
	  FrameAssertion.contains(getCurrentBuyer.toString(), amwayId, "验证当前购货人");
	  String goodsAmount = shoppingCart.getGoodsAmount();
	  FrameAssertion.contains(goodsAmount, "2", "验证婴儿沐浴露数量");
	  String bv = shoppingCart.getBV();
	  FrameAssertion.contains(bv, "276.80", "验证净营业额");
	  String pv = shoppingCart.getPV();
	  FrameAssertion.contains(pv, "22.16", "验证销售指数");
	  List<String> moneyAmount = shoppingCart.getMoneyAmount();
	  FrameAssertion.contains(moneyAmount.toString(), "320", "验证金额小计");
	  List<String> totalAmount = shoppingCart.getTotalMoneyAmount();
	  FrameAssertion.contains(totalAmount.toString(), "320", "验证总金额");
	  
	  //结算
	  shoppingCart.confirm();
	  Settlement settlement = new Settlement(androidDriver);
	  List<String> multipleValues = settlement.getMultipleValues();
	  FrameAssertion.contains(multipleValues.toString(), "276.80", "验证净营业额");
	  FrameAssertion.contains(multipleValues.toString(), "22.16", "验证销售指数");
	  FrameAssertion.contains(multipleValues.toString(), "330", "验证订单总金额");
	  
	  String buyAmount = settlement.getBuyAmount();
	  FrameAssertion.contains(buyAmount, "320", "验证购货总额");
	  String freight = settlement.getFreight();
	  FrameAssertion.contains(freight, "10", "验证运费");
	  String deductedPoints = settlement.getDeductedPoints();
	  FrameAssertion.contains(deductedPoints, "0", "验证扣减悦享分");
	  
	  //支付
	  Payment payment = new Payment(androidDriver);
	  payment.goToPayment();
	  payment.paymentSelection("微信支付");
	  payment.doPayment();
	  payment.cancelPayment();
	  List<String> validation = payment.paymentIncompleteValidation();
	  FrameAssertion.contains(validation.toString(), "您的支付未完成", "验证支付成功信息");
	  
  }
  
}











