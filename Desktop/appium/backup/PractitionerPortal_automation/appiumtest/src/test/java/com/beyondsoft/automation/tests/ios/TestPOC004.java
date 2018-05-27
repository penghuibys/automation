
package com.beyondsoft.automation.tests.ios;

import org.testng.annotations.Test;
import frame.com.pp.auto.action.FrameAssertion;
import java.net.MalformedURLException;
import java.util.List;
import com.beyondsoft.automation.pages.ios.Login;
import com.beyondsoft.automation.pages.ios.MainPage;
import com.beyondsoft.automation.pages.ios.Payment;
import com.beyondsoft.automation.pages.ios.HuangHouChaoGuo;
import com.beyondsoft.automation.pages.ios.ShoppingCart;
import com.beyondsoft.automation.base.Setup;
import com.beyondsoft.automation.pages.ios.Settlement;


public class TestPOC004 extends Setup{
	

	
  @Test
  public void mobileTestPOC004() throws InterruptedException, MalformedURLException {
	  setUpIosDriver();
	  setUpUser();
	  
	  
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
	  String getCurrentBuyer = shoppingCart.getCurrentBuyer();
	  FrameAssertion.contains(getCurrentBuyer.toString(), amwayId, "验证当前购货人");
	  List<String> moneyAmount = shoppingCart.getMoneyAmount();
	  FrameAssertion.contains(moneyAmount.toString(), "2,500", "验证总金额");
	  List<String> promotionGifts = shoppingCart.getPromotionGift();
	  FrameAssertion.equals(promotionGifts.size(), 3, "验证附件个数");
	  
	  shoppingCart.confirm();
	  
	  Settlement settlement = new Settlement(iosDriver);
	  String defaultDelivery = settlement.getDefaultDelivery();
	  FrameAssertion.equals(defaultDelivery, "1", "默认配送方式");//默认配送方式周一至周日：选中为1, 未选则为0
	  
	  settlement.requireInvoice();
	  settlement.selectVATSpecialInvoice();
	  List<String> totalAmount = settlement.getTotalAmount();
	  FrameAssertion.contains(totalAmount.toString(), "2,500", "验证总金额");
	  
	  Payment payment = new Payment(iosDriver);
	  payment.goToPayment();
	  payment.doPayment();
	  payment.paymentSelection("支付宝");
	  payment.paymentConfirmation();
	  payment.paymentCompletion();
	  List<String> validation = payment.paymentValidation();
	  List<String> amount =  payment.paymentAmount();
	  FrameAssertion.contains(validation.toString(), "您已成功支付", "验证支付成功信息");
	  FrameAssertion.contains(amount.toString(), "2,500", "验证支付成功信息");

  }
  
}











