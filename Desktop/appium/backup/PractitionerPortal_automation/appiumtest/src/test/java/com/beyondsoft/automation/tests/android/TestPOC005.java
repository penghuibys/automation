
package com.beyondsoft.automation.tests.android;

import org.testng.annotations.Test;
import frame.com.pp.auto.action.FrameAssertion;
import java.net.MalformedURLException;
import java.util.List;
import com.beyondsoft.automation.pages.android.MainPage;
import com.beyondsoft.automation.pages.android.Payment;
import com.beyondsoft.automation.pages.android.PersonalCare;
import com.beyondsoft.automation.pages.android.ShoppingCart;
import com.beyondsoft.automation.base.Setup;
import com.beyondsoft.automation.pages.android.Settlement;
import com.beyondsoft.automation.pages.android.ChangeShopper;
import com.beyondsoft.automation.pages.android.Order;


public class TestPOC005 extends Setup{

	
  @Test
  public void mobileTestPOC005() throws InterruptedException, MalformedURLException {
	  setUpAndroidDriver();
	  setUpUser();
	  
	  MainPage mainPage = new MainPage(androidDriver);
	  mainPage.navigateToAmywayCloudShopping();
	  
	  ChangeShopper change = new ChangeShopper(androidDriver);
	  change.changeShopperAndConfirm();
	  String currentShopper = change.getCurrentShopper();
	  FrameAssertion.contains(currentShopper, "48678350", "验证当前购货人");
	  
	  PersonalCare personCare = new PersonalCare(androidDriver);
	  personCare.searchBabyShampoo();
	  personCare.setQuantity();
	  
	  ShoppingCart shoppingCart = new ShoppingCart(androidDriver);
	  shoppingCart.addToShoppingCart();
	  shoppingCart.goToShoppingCart();
	  
	  //购物车
	  List<String> getCurrentBuyer = shoppingCart.getCurrentShopper();
	  FrameAssertion.contains(getCurrentBuyer.toString(), "48678350", "验证当前购货人");
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
	  FrameAssertion.contains(multipleValues.toString(), "332", "验证订单总金额");
	  
	  String buyAmount = settlement.getBuyAmount();
	  FrameAssertion.contains(buyAmount, "320", "验证购货总额");
	  String freight = settlement.getFreight();
	  FrameAssertion.contains(freight, "12", "验证运费");
	  String deductedPoints = settlement.getDeductedPoints();
	  FrameAssertion.contains(deductedPoints, "0", "验证扣减悦享分");
	  
	  //支付
	  Payment payment = new Payment(androidDriver);
	  payment.goToPayment();
	  
	  payment.multiPayment("200");
	  payment.paymentSelection("微信支付");
	  payment.doPayment();
	  payment.paymentCompletion();
	  
	  List<String> validation = payment.paymentValidation();
	  FrameAssertion.contains(validation.toString(), "您已成功支付", "验证支付成功信息");

	  //订单
	  Order order = new Order(androidDriver);
	  order.goToOrderDetails();
	  
	  String payed = order.getPayed();
	  FrameAssertion.contains(payed, "200", "验证已支付金额");
	  String toPay = order.getToPay();
	  FrameAssertion.contains(toPay, "132", "验证剩余应付金额");
	  
	  order.doPayment();//订单页面 立即支付
	  payment.doPayment();//支付页面 立即支付
	  payment.paymentCompletion();
	  order.goToOrderDetails();
	  
	  String orderOperator =  order.getOrderOperator();
	  String channel =  order.getChannel();
	  String orderStatus =  order.getOrderStatus();
	  String paymentInfo =  order.getPaymentInformation();
	  payed = order.getPayed();
	  toPay = order.getToPay();
	  List<String> multiValidation = order.getMultiValidation();
	  FrameAssertion.contains(payed, "332", "验证已支付金额");
	  FrameAssertion.contains(toPay, "0", "验证剩余应付金额");
	  FrameAssertion.contains(orderOperator, amwayId, "验证订单操作人");
	  FrameAssertion.contains(channel, "云服务", "验证购货渠道");
	  FrameAssertion.contains(orderStatus, "付款成功", "验证订单状态");
	  FrameAssertion.contains(paymentInfo, "付款信息", "验证多笔支付");
	  FrameAssertion.contains(multiValidation.toString(), "276.8", "验证净营业额");
	  FrameAssertion.contains(multiValidation.toString(), "22.16", "验证销售指数");
	  FrameAssertion.contains(multiValidation.toString(), "332", "验证总金额");
  }
  
}











