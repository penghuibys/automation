package frame.com.mtf.ibm.init;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import frame.com.mtf.ibm.universal.ReadFile;

import frame.com.mtf.ibm.init.IOSInit;


public class AndroidInit {
		
	/**
	 * @notice what's the difference between  AppiunDriver, IOSDriver and AndroidDriver
	 * 1,There is an abstract AppiumDriver class which inherits from the Selenium Java Client
	 * 2,The AppiumDriver class contains all methods shared by iOS and Android
	 * 3,IOSDriver and AndroidDriver both extend AppiumDriver and provide more methods, and specific implementations for some methods
	 */
	public static AndroidDriver<WebElement> androidDriver;
	public static IOSDriver<WebElement> IOSDriver;
	
	ReadFile RF = new ReadFile(null, androidDriver);
	private final String appPackage =  RF.xmlValue("androidAppPackage");
	private final String appActivity =  RF.xmlValue("androidAppActivity");
	private final String appName =  RF.xmlValue("androidAppName");
	private final String URL = RF.xmlValue("URL");

	
	
	
	
	public AndroidDriver<WebElement>launchApp(){

//		File app = new File("mobileApp/" + "weixin_1300.apk");//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName","huawei mate9");
//        capabilities.setCapability("platformName", "android");
//        capabilities.setCapability("app", app.getAbsolutePath());   
//        capabilities.setCapability("unicodeKeyboard", true);//Chinese input 
//        capabilities.setCapability("resetKeyboard", true);//hide keyboard
//        capabilities.setCapability("noReset", true);
//		
//		try {
//			androidDriver = new AndroidDriver<WebElement>(new URL("http://" + URL +"/wd/hub"), capabilities);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}	
      
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PLATFORM, "android");


		String BROSWER_NAME = System.getProperty("BROSWER_NAME");
		String APPIUM_PLATFORM = System.getProperty("APPIUM_PLATFORM");
		String APPIUM_DEVICE_VERSION = System.getProperty("APPIUM_DEVICE_VERSION");
		String APPIUM_DEVICE_NAME = System.getProperty("APPIUM_DEVICE_NAME");
		String APPIUM_DEVICE_UDID = System.getProperty("APPIUM_DEVICE_UDID");
		String APPIUM_APP_FILE = System.getProperty("APPIUM_APP_FILE");
		String APPIUM_URL = System.getProperty("APPIUM_URL");
		
		File app = new File(APPIUM_APP_FILE);
		System.out.println(app.getAbsolutePath());
		
		if (!isEmpty(BROSWER_NAME))
		{
		    capabilities.setCapability(CapabilityType.BROWSER_NAME, BROSWER_NAME);
		}
		if (!isEmpty(APPIUM_PLATFORM))
		{
		    capabilities.setCapability("platformName", APPIUM_PLATFORM);
		}
		if (!isEmpty(app.getAbsolutePath()))
		{
			capabilities.setCapability("app", app.getAbsolutePath());
		}
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("unicodeKeyboard", true);//Chinese input 
		capabilities.setCapability("resetKeyboard", true);//hide keyboard

		if (!isEmpty(APPIUM_DEVICE_VERSION))
		{
			capabilities.setCapability("platformVersion", APPIUM_DEVICE_VERSION);
		}
		if (!isEmpty(APPIUM_DEVICE_NAME))
		{
			capabilities.setCapability("deviceName", APPIUM_DEVICE_NAME);
		}

		if (!isEmpty(APPIUM_DEVICE_UDID))
		{
			capabilities.setCapability("udid", APPIUM_DEVICE_UDID);
		}
		
		
		try {
			androidDriver = new AndroidDriver<WebElement>( new URL(APPIUM_URL), capabilities);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
       
        
       
		return androidDriver;
	}
	
	//there is no the third party browser support for appium except Chrome sofa, so it's hard code
	public  AndroidDriver<WebElement> launchChrome(){
		
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("unicodeKeyboard", true);//Chinese input 
        //capabilities.setCapability("resetKeyboard", true);//hide keyboard
        try {
        	androidDriver = new AndroidDriver<WebElement>(new URL("http://" + URL +"/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return androidDriver;      
	}

	
	public AndroidDriver<WebElement> fullLaunchApp(String appURL){
		
		String getAppAbsPath = downloadApp(appURL);
		// for Mac only so far
		String getPackageActivity = "/Volumes/IDISK/My_SDK/sdk/platform-tools/aapt dump badging " + getAppAbsPath;
		List<String> getPA = packageActivity(getPackageActivity);		
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("app", getAppAbsPath);
        capabilities.setCapability("appPackage", getPA.get(0));
        capabilities.setCapability("appActivity",getPA.get(1));
        capabilities.setCapability("unicodeKeyboard", true);//Chinese input 
        //capabilities.setCapability("resetKeyboard", true);//hide keyboard
        try {
        	androidDriver = new AndroidDriver<WebElement>(new URL("http://" + URL +"/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
        return androidDriver;
	}
	
	/**
	 * 
	 * @param apkPath
	 * @return package[0] and  activity[1] 
	 * @note if environment variable is NOT ready for aapt command, refer to below format
	 * 		 the absolute location of aapt command and give the absolute application location as well
	 * 
	 * /Volumes/IDISK/My_SDK/sdk/platform-tools/aapt dump badging /Users/location/Downloads/application.apk
	 */
	public List<String> packageActivity(String apkPath){
		
		List<String> packageActivity = new ArrayList<String>();
		
		try {
			//run command under terminal , "/Volumes/IDISK/My_SDK/sdk/platform-tools/aapt dump badging "  if no environment variable
			Process process = Runtime.getRuntime().exec("/Volumes/IDISK/My_SDK/sdk/platform-tools/aapt dump badging " + apkPath);
			//get data from stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));  			     
			//save the data			     
			String line = null;
			String packageFormat = "package: name=";
			String activityFormat = "launchable-activity: name=";
			//read and write  
			while ((line = reader.readLine()) != null) {
				if (line.contains(packageFormat) || line.contains(activityFormat)){
					//extract package name and activity name into list 
					String splitName[] = line.split("name='");
					String newName[] = splitName[1].split("' ");
					packageActivity.add(newName[0]);
				} 
			   }   
			   process.getOutputStream().close(); 
			  } catch (Exception e) {
				  e.printStackTrace();
				  packageActivity.add("[Info]:Unable to find package and activity, please do it manually,or check your environment variable is ready for aapt command under Android SDK");			  
			  }   
		return packageActivity;
	}
	
	/**
	 * @purpose download application and save to folder then return the full path of application 
	 * @param appURL
	 * @return apk full path
	 * @note sometime need to download application from the home page to simulator the user as first time to install our application
	 */
	public static String downloadApp(String appURL){

		String getName = getAPKName(appURL);
		//give the download path  and application full name to save
		File f = new File("Applications/Download/",getName); 
		try {
			System.out.println("[Info:]tring to download application from url,be patient");
			URL url = new URL(appURL);
			FileUtils.copyURLToFile(url,f);
			System.out.println("[Info:]the application has down successfully");
		} catch (Exception e) {
			System.out.println("[Info:] unable to download from this url: " + appURL);
			System.out.println("[Info:] please do it manually or check your URL or network");
		}
		
		File classpathRoot = new File(System.getProperty("user.dir"));
		// the appium install application to device must be absolute path
		return classpathRoot + "/" +f.toString();
	}
	
	/**
	 * @purpose rename application with download time
	 * @param url
	 * @return apk name
	 * @note the apk name will be replaced as apk name with download time
	 */
	public static String getAPKName(String url){
		//the last "/" of url address is the application name, split from last "/" to get fully name of application,like below
		//https://www.webpage/application.apk		
					
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//time format
		String getNowTime = df.format(new Date());// new Date() to get now time		
		//get application name first from the url
		int splitURL = url.lastIndexOf("/");
		String apkName = url.substring(splitURL + 1);
		//replace apk name and add the download time as application full name to return
		String[] reApkName = apkName.split(".apk");		
		String appName = reApkName[0] + "_" + getNowTime + ".apk";		
		return appName;
	}
	
	private boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			
			return true;
		} 
		return false;
	}
	
}
