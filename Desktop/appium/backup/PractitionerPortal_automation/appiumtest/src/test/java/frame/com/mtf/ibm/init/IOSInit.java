package frame.com.mtf.ibm.init;

import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import frame.com.mtf.ibm.universal.ReadFile;


public class IOSInit {
	
	ReadFile RF = new ReadFile();
	private IOSDriver<WebElement> driver;
	private String URL = RF.xmlValue("URL");
	private String simuAppLocation = RF.xmlValue("iOSsimuAppLocation");
	private String udid = RF.xmlValue("iOSudid");
	//private static String bundleID = "com.ibm.gbs.mobileAutomation.salestracker";
	private String bundleID = RF.xmlValue("iOSBundleID");	
	
	public IOSDriver<WebElement> launchSimuApp(){
		
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS"); 
        capabilities.setCapability("deviceName","iPhone 6");
        capabilities.setCapability("platformVersion", "9.1");
        capabilities.setCapability("app", simuAppLocation);
        try {
			driver = new IOSDriver<WebElement>(new URL("http://" + URL +"/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return driver; 
	}
		
	public IOSDriver<WebElement> launchRealApp() throws MalformedURLException{
		
		/*
		 * @note: Launch app on real device
		 * 1, source code and cert must available
		 * 2, if app has installed , just use bundleID can launch app
		 * 3, the mobile device have to enable UI Automation from developer
		 * 4, deviceName can not empty , the device name is hard code but can be wrong name
		 * 5, if hybrid page,input ios_webkit_debug_proxy -c e51f816ea8511c59bcc631fe89b7049599d2f21a:27753 -d under command
		 */			
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName","iOS"); 
//        capabilities.setCapability("deviceName","iOS Device");
//        capabilities.setCapability("platformVersion", "8.3");
//        capabilities.setCapability("udid", udid);
//        capabilities.setCapability("bundleId", bundleID);
//        try {
//        	driver = new IOSDriver<WebElement>(new URL("http://" + URL +"/wd/hub"), capabilities);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
        
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "iPhone 7");
		capabilities.setCapability(CapabilityType.VERSION, "10.3");
		capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
		capabilities.setCapability("udid", "8522f4c2c74501a6c29c36846b7c4db50679bd7b");
		capabilities.setCapability("bundleid", "com.amway.amhubPhone");
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("noReset", true);
		
		capabilities.setCapability("app", "/Users/devicepass/Desktop/appium/AmwayHubML_iPhone_v3.49.0_QA_out_2.ipa");
		driver = new IOSDriver( new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
		
        return driver; 
	}
	
	public IOSDriver<WebElement> launchSimuSafari(){
		
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS"); 
        capabilities.setCapability("deviceName","iPhone 6 Plus");
        capabilities.setCapability("platformVersion", "8.3");
        capabilities.setCapability("browserName", "Safari");
		try {
			 driver = new IOSDriver<WebElement>(new URL("http://" + URL +"/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;         
	}
	
	public IOSDriver<WebElement> launchRealDevWebview(){
	   /*
	    * Launch webview app for web testing
	    * 1, launch app a bit solw around 30s 
	    * 2, MUST lanuch ios_webkit_debug_proxy -c e51f816ea8511c59bcc631fe89b7049599d2f21a:27753
	    * 3, switch to webview mode
	    */
       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("platformName","iOS"); 
       capabilities.setCapability("deviceName","iOS Device");
       capabilities.setCapability("platformVersion", "8.3");
       capabilities.setCapability("udid", udid);
       capabilities.setCapability("bundleId", "com.WebViewApp");
       try {
    	   driver = new IOSDriver<WebElement>(new URL("http://" + URL +"/wd/hub"), capabilities);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return driver;
	}
}
