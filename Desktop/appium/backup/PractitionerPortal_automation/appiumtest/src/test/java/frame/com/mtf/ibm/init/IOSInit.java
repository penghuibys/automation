package frame.com.mtf.ibm.init;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import frame.com.mtf.ibm.universal.ReadFile;


public class IOSInit {
	
	private IOSDriver<WebElement> driver;
	ReadFile RF = new ReadFile(driver, null);
	
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
 
		//local run
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "iPhone 7");
		capabilities.setCapability(CapabilityType.VERSION, "10.3");
		capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
		capabilities.setCapability("udid", "8522f4c2c74501a6c29c36846b7c4db50679bd7b");
		capabilities.setCapability("bundleid", "com.amway.amhubPhone");
		capabilities.setCapability("automationName", "XCUITest");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("app", "/Users/devicepass/Desktop/appium/AmwayHubML_iPhone_v3.49.0_QA_out_2.ipa");
		driver = new IOSDriver<WebElement>( new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
		

//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability(CapabilityType.VERSION, "10.3");
//		capabilities.setCapability(CapabilityType.PLATFORM, "iOS");
//		capabilities.setCapability("automationName", "XCUITest");
//
//
//		String BROSWER_NAME = System.getProperty("BROSWER_NAME");
//		String APPIUM_PLATFORM = System.getProperty("APPIUM_PLATFORM");
//		String APPIUM_DEVICE_VERSION = System.getProperty("APPIUM_DEVICE_VERSION");
//		String APPIUM_DEVICE_NAME = System.getProperty("APPIUM_DEVICE_NAME");
//		String APPIUM_DEVICE_UDID = System.getProperty("APPIUM_DEVICE_UDID");
//		String APPIUM_APP_FILE = System.getProperty("APPIUM_APP_FILE");
//		String APPIUM_URL = System.getProperty("APPIUM_URL");
//		
//		File app = new File(APPIUM_APP_FILE);
//		System.out.println(app.getAbsolutePath());
//		
//		if (!isEmpty(BROSWER_NAME))
//		{
//		    capabilities.setCapability(CapabilityType.BROWSER_NAME, BROSWER_NAME);
//		}
//		if (!isEmpty(APPIUM_PLATFORM))
//		{
//		    capabilities.setCapability("platformName", APPIUM_PLATFORM);
//		}
//		if (!isEmpty(app.getAbsolutePath()))
//		{
//			capabilities.setCapability("app", app.getAbsolutePath());
//		}
//		capabilities.setCapability("noReset", true);
//
//		if (!isEmpty(APPIUM_DEVICE_VERSION))
//		{
//			capabilities.setCapability("platformVersion", APPIUM_DEVICE_VERSION);
//		}
//		if (!isEmpty(APPIUM_DEVICE_NAME))
//		{
//			capabilities.setCapability("deviceName", APPIUM_DEVICE_NAME);
//		}
//
//		if (!isEmpty(APPIUM_DEVICE_UDID))
//		{
//			capabilities.setCapability("udid", APPIUM_DEVICE_UDID);
//		}
//		
//		
//		driver = new IOSDriver<WebElement>( new URL(APPIUM_URL), capabilities); 

		
		
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
	
	private boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			
			return true;
		} 
		return false;
		
	}
}
