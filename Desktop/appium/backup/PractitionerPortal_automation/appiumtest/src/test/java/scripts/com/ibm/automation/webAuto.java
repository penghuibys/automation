package scripts.com.ibm.automation;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ibm.pp.common.CommonPage;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.action.PageAction;
import frame.com.pp.auto.base.TestBase;
import frame.com.pp.auto.driver.BrowserType;

public class webAuto extends TestBase {

	public CommonPage comm;
	public PageAction page;
	public String userName;
	public String password;
	public String url;
	
	@BeforeClass
	public void setUp() {
		setBrowser(System.getProperty("testing_browser") == null ? BrowserType.FIREFOX
				: BrowserType.getType(System.getProperty("testing_browser")));
		initPage();
		
		userName = comm.getUserInfo("testdata.xlsx", "userInfo", "UserProd", "PasswordProd", 
				"UserStage", "PasswordStage","UserTest", "PasswordTest").get("username");
		password = comm.getUserInfo("testdata.xlsx", "userInfo", "UserProd", "PasswordProd", 
				"UserStage", "PasswordStage","UserTest", "PasswordTest").get("password");
	}
	
	private void initPage(){
		comm = new CommonPage(action);
	}

	@Test
	public void TestDemo(){
		
    	url = comm.getURL("testdata.xlsx", "ENV", "URLProd", "URLStage", "URLTest" );
    	
	 	comm.loginPractitionerPortal(userName,password);
	 	action.sleep(5, "sleep");
	 	
	 	boolean loginflag = false;
	 	loginflag = action.verifyDisplay(By.xpath(".//*[@id='search-view']//div[@class='box']"), "web application access");
	 	FrameAssertion.isTrue(loginflag, "Verify access to main page.");
	 	
	 	String searchPortal = null;
	 	searchPortal = action.getText(By.xpath(".//*[@id='search-view']//div[@class='box']/h2"), "Get main page text");
	 	FrameAssertion.equals(searchPortal, "Search the portal", "Verify search portal text.");
	}
	
}
