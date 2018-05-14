
package scripts.com.ibm.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import frame.com.mtf.ibm.init.AndroidInit;
import frame.com.mtf.ibm.init.IOSInit;
import frame.com.mtf.ibm.operation.Locate;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.base.TestBase;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;


public class mobileAuto extends TestBase{
	
	public String username;
	public String password;
	public String username_l;
	public String password_l;
	public String year1;
	public String year2;
	public AndroidInit android;
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
	  signIn();
	  Locate.click(iosDriver, "安利云购");

	  Locate.clickIfItemDisplayed(iosDriver, "我知道了");
	  
	  Locate.click(iosDriver, "搜索");
	  Locate.send(iosDriver,  "输入框", "饮品券");
	  Locate.click(iosDriver, "搜索");
	  boolean isSearchResultDisplayed = Locate.validateElementDisplayed(iosDriver, "RICHJAY咖啡饮品券");
	  FrameAssertion.isTrue(isSearchResultDisplayed, "搜索结果显示.");
	  

	//  action.sleep(5, "");
	  
  }
  
  public void signIn() throws InterruptedException {
	  Locate.send(iosDriver, "username", "58374544");
	  Locate.send(iosDriver, "password", "123456");
	  Locate.clickByLabelText(iosDriver, "登   录");
  }
  
  
}











