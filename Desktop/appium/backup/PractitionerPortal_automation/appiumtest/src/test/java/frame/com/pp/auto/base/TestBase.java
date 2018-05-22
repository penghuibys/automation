package frame.com.pp.auto.base;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import frame.com.pp.auto.action.FrameAction;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.data.TestData;
import frame.com.pp.auto.driver.BrowserType;
import frame.com.pp.auto.driver.DriverSelector;
import frame.com.pp.auto.log.FrameLog;
import frame.com.pp.auto.log.LogUtil;
import frame.com.pp.auto.precheck.PreCheck;
import frame.com.pp.auto.report.FrameReporter;
//import frame.com.pp.auto.report.excel.ExcelReportGenerator;
import frame.com.pp.auto.report.html.HtmlReportGenerator;
import frame.com.pp.auto.report.model.CaseModel;
//import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumDriver;

@Listeners(FrameListener.class)
public class TestBase {
	
	protected WebDriver driver;
	protected AppiumDriver<WebElement> iosDriver;
	protected AppiumDriver<WebElement> androidDriver;
//	protected AppiumDriver driver_;
	protected FrameAction action;
	protected TestData data;
	protected FrameAssertion assertion;
	
	protected TestBase(){
		this.data = new TestData();
	}
	
//	public void setBrowser() {
//		setBrowser(BrowserType.FIREFOX);
//	}
	
	public void setMobileDriver(WebDriver d) {
		this.driver = d;
		this.action = new FrameAction(this.driver);
		this.assertion = new FrameAssertion(iosDriver, androidDriver);
	}
	
	public void setBrowser(BrowserType browserType) {
		this.driver = new DriverSelector().setDriver(browserType);
		this.action = new FrameAction(this.driver);
		this.assertion = new FrameAssertion(iosDriver, androidDriver);
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	//Get environment data: Prod, Stage, Test. Default as Prod.
	public HashMap<String,String> getEnvData(HashMap<String,String> prod,HashMap<String,String> stage,
					HashMap<String,String> test){
		if (System.getProperty("env")!= null && System.getProperty("env").equalsIgnoreCase("stage"))
			return stage;
		else if (System.getProperty("env")!= null && System.getProperty("env").equalsIgnoreCase("prod"))
			return prod;
		else
			return test;
	}
	
//	@BeforeTest
	public void bTest(){
		PreCheck.getInstance().preCheck();
	}
	
	@BeforeClass
	public void bClass(){
		LogUtil.info("-------Start to Run Class-----------");
	}
	
	@AfterClass
	public void aClass(){
		LogUtil.info("-------Finished Test Class-----------");
		if (action != null){
			//	action.tearDown("-------Finished Test Class-----------");
				this.driver.quit();
			}
	}
	
	@AfterTest
	public void aTest(){
		List<CaseModel> tc = FrameReporter.getInstance().getTestCases();
		if (tc != null && !tc.isEmpty()) {
		//	new ExcelReportGenerator(tc).createExcelReport();
			new HtmlReportGenerator(tc).createHtmlReport();
			new FrameLog().createLogFile(LogUtil.loglist);
		}
	}
}
