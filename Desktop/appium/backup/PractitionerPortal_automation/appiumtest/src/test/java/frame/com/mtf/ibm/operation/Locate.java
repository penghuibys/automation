package frame.com.mtf.ibm.operation;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;

import org.apache.log4j.DailyRollingFileAppender;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import frame.com.mtf.ibm.universal.ReadFile;
import frame.com.mtf.ibm.universal.SystemRelated;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.log.LogUtil;
import frame.com.pp.auto.util.SysUtil;




public class Locate  {
	/**
	 * @purpose  according to the xlsx data to simplify the action stream
	 * @note the timeout can be adjustment , the attribute will be change to lower case automatically
	 * @notice	WebElement is appium's element which subclasses WebElement with more FUNCTIONs and 
	 * 			adds appium-specific features (like being able to perform Touch Gestures(swipe,tap etc)).
	 * 
	 * @caution all the operations support static "Attribute" in xlsx sheet only, for temporary "Attribute" only isDisplayed function available
	 * 			temporary example：
	 * 			WebElement object = Locate.isDisplayed(driver,"id","id attribute",30(optional));
	 * 					   object.click();
	 * 					   object.clear();
	 * 					   object.sendKeys("content");
	 */	
	
	//Forbidden to create an object
//	private Locate(){}
	public Locate(AppiumDriver<WebElement> iOSDriver, AppiumDriver<WebElement> androidDriver){
		Locate.iOSDriver = iOSDriver;
		Locate.androidDriver = androidDriver;
	}
	
	

	private static int timeout = 60;//second
	public static AppiumDriver<WebElement> androidDriver;
	public static AppiumDriver<WebElement> iOSDriver;
	private static String timeFormat = "yyyy/MM/dd/ HH:mm:ss";
	
	
	/**
	 * @purpose due to network, device performance,other concerns, will do the next action when element is displayed 
	 * @param dr
	 * @param target
	 * @return after the element is showing then return the element for others action
	 */

	
	public WebElement isDisplayed(AppiumDriver<WebElement> dr, String by, String target, int timeout){
		
		WebElement element = null;
		String attribute = null;
		String object = null;
		//for temporary element purpose		
		if (by != null){			
			object = target;
			attribute = by;
		} else {
			ReadFile rf = new ReadFile(iOSDriver, androidDriver);
			object = rf.getObject(target).get(0);
			attribute = rf.getObject(target).get(1);
		}
		switch (attribute.toLowerCase()) {	
	    	case "id":
		    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.id(object)));
		    		element = dr.findElement(By.id(object));
		    		element(element);	    		
		    		break;
	    	case "name":
	    	//	try {
	    	//		element = dr.findElement(By.name(object));
	    	//		element(element);
			//	} catch (Exception e) {
		    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.name(object)));
		    		element = dr.findElement(By.name(object));
		    		element(element);
			//	}

	    		break;
	    	case "classname":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.className(object)));
	    		element = dr.findElement(By.className(object));
	    		break;
	    	case "xpath":
	    	//	try {
	    	//		element = dr.findElement(By.xpath(object));
	    	//		element(element);
			//	} catch (Exception e) {

		    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(object)));
		    		element = dr.findElement(By.xpath(object));
		    		element(element);
			//	}
	    		break;
	    	case "linktext":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.linkText(object)));
	    		element = (WebElement) dr.findElement(By.linkText(object));
	    		break;
	    	case "tagname":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.tagName(object)));
	    		element = (WebElement) dr.findElement(By.tagName(object));
	    		break;
	        case "css":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(object)));
	    		element = (WebElement) dr.findElement(By.cssSelector(object));
	    		break;   
	        case "accessibilityid":
	        	new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(object)));
	  		  	SysUtil.sleep(5);
	        	element = (WebElement) dr.findElementByAccessibilityId(object);
		    	break;    
	        default : 
	        	System.out.println("[Info:] Your attribute " + attribute + " is not correct or empty for selenium,the API such as: id,name,etc");
	        	break;
			}
		return element;
	}
	
	public static WebElement element(WebElement element) {
		if (element.isDisplayed()) {
			return element;
		} else {
			return null;
		}
	}
	
	
	public static List<WebElement> elements(AppiumDriver<WebElement> dr, String by, String target, int timeout){
		
		List<WebElement> elements = null;
		String attribute = null;
		String object = null;
		//for temporary element purpose		
		if (by != null){			
			object = target;
			attribute = by;
		} else {
			ReadFile rf = new ReadFile(iOSDriver, androidDriver);
			object = rf.getObject(target).get(0);
			attribute = rf.getObject(target).get(1);
		}
		switch (attribute.toLowerCase()) {	
	    	case "id":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.id(object)));
	    		elements = dr.findElements(By.id(object));
	    		break;
	    	case "name":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.name(object)));
	    		elements = dr.findElements(By.name(object));
	    		break;
	    	case "classname":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.className(object)));
	    		elements = dr.findElements(By.className(object));
	    		break;
			case "xpath":
				new WebDriverWait(dr, timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(object)));
				elements = dr.findElements(By.xpath(object));
				break;
	    	case "linktext":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.linkText(object)));
	    		elements = dr.findElements(By.linkText(object));
	    		break;
	    	case "tagname":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.tagName(object)));
	    		elements = dr.findElements(By.tagName(object));
	    		break;
	        case "css":
	    		new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(object)));
	        	elements = dr.findElements(By.cssSelector(object));
	    		break;   
	        default : 
	        	System.out.println("[Info:] Your attribute " + attribute + " is not correct or empty for selenium,the API such as: id,name,etc");
	        	break;
			}
		return elements;
	}
	//overloading for time out which can be adjustment for mobile only
	public  WebElement isDisplayed(AppiumDriver<WebElement> driver,String by,String target){
		
		return isDisplayed(driver,by,target,timeout);
	}
		
	public  WebElement isDisplayed(AppiumDriver<WebElement> driver,String target,int timeout){
		
		return isDisplayed(driver,null,target,timeout);
	}
	
	
	public WebElement isDisplayed(String target){
		return isDisplayed(androidDriver,null,target,timeout);
	}
	
	public WebElement element(AppiumDriver<WebElement> driver,String target){
		
		return isDisplayed(driver,null,target,timeout);
	}
	
	public List<WebElement> elements(AppiumDriver<WebElement> driver,String target){
		
		return elements(driver,null,target,timeout);
	}
	

	public void click(AppiumDriver<WebElement> driver,String target) throws InterruptedException{
		
		try{
			WebElement element = element(driver, target);
			element.click();
			LogUtil.step("Clicked at '" + target + "', Passed");
		}
		catch(Exception e){
			FrameAssertion.fail("Clicked at '" + target + "', Failed");
		}
	}

	
	public void atScreen(AppiumDriver<WebElement> driver,String target) throws InterruptedException{
		
		try{
			element(driver, target);
			LogUtil.step("Current Screen is dislayed,  contains text '" + target + "', Passed");
		}
		catch(Exception e){
			FrameAssertion.fail("Current Screen is Not contains text '" + target + "', Failed");
		}
	}
	
	public void clickIfItemDisplayed(AppiumDriver<WebElement> driver,String target) throws InterruptedException{
		
		WebElement element = null;
		try {
			element = this.isDisplayed(driver, target, 20);
			SysUtil.sleep(5);
			element.click();
			LogUtil.step("Clicked at '" + target + "', Passed");
		} catch (Exception e) {
			LogUtil.info(target + " is not displayed.");
		}
	}
	
	public boolean validateElementDisplayed(String target) throws InterruptedException{
		
		try{
			WebElement element = element(iOSDriver, target);
			return element.isDisplayed();
		}
		catch(Exception e){
			return false;
		}
	}
	
	public void clickIfItemDisplayed(String target) throws InterruptedException {
		

		if (androidDriver != null) {
			clickIfItemDisplayed(androidDriver, target);
		}
		
		if (iOSDriver != null) {
			clickIfItemDisplayed(iOSDriver, target);
		}
	}
	
	public void clickByLabelText(String target) throws InterruptedException{
		
		try{
			List<WebElement> elements = elements(iOSDriver, target);
			for (int i = 0; i < elements.size(); i++) {
				 if (elements.get(i).getAttribute("label").contains(target)) {
					 elements.get(i).click();
					 break;
				 }
			}
			LogUtil.step("Clicked at '" + target + ", Passed");
		}
		catch(Exception e){
			FrameAssertion.fail("Clicked at '" + target + ", Failed");
		}
	}
	
	public void click(String target) throws InterruptedException{
		
		if (androidDriver != null) {
			click(androidDriver,target);
		}
		
		if (iOSDriver != null) {
			click(iOSDriver,target);
		}
	}
	
	
	public void atScreen(String target) throws InterruptedException{
		
		if (androidDriver != null) {
			atScreen(androidDriver,target);
		}
		
		if (iOSDriver != null) {
			atScreen(iOSDriver,target);
		}
	}
		
	public void send(AppiumDriver<WebElement> driver, String target, String content) throws InterruptedException {
		WebElement element = element(driver, target);
		element.clear();
		element.sendKeys(content);
		LogUtil.step("Send Key '" + content + "' to " + target + ", Passed");
	}
	
	public void send(String target, String content) throws InterruptedException{
		
		
		if (androidDriver != null) {
			send(androidDriver, target, content);
		}

		if (iOSDriver != null) {
			send(iOSDriver, target, content);
		}

	}
	
	/**
	 * 
	 * @param driver
	 * @param name
	 * @throws InterruptedException
	 * 
	 * @notice this method will help to find the element and the page will swipe automatically until the element found or out of swipe time
	 */
	public void swipeLocate(AppiumDriver<WebElement> driver, String by, String name, int swipeTime) throws InterruptedException{		
		int countTime = 0;
		while (true){
			try {
				WebElement dr = isDisplayed(driver, by, name, 1);
				//dr.click();
				LogUtil.info("[Info] Located at :" + name);
				break;
			} catch (Exception e) {
				countTime = countTime + 1;
				//swipeAction("up");
				LogUtil.info("[Info] swiped and find again..." + countTime + " time(s)");
				
				if (countTime == swipeTime){
					LogUtil.info("[Info]swipe stoped, unable to find element or current page has issue.");
					break;
				}
			}			
		}
	}
		
	// post other By method, such as id , className, xpath
	public void swipeLocate(AppiumDriver<WebElement> driver, String by,String name) throws InterruptedException{
		
		swipeLocate(driver , by, name, 20);
	}
	
	//By name , always to use as swipe locate, so create one method for it only
	public void swipeLocate(AppiumDriver<WebElement> driver,String name, int swipeTime) throws InterruptedException{
		
		swipeLocate(driver,"name", name , swipeTime);
	}
	
	public void swipeLocate(AppiumDriver<WebElement> driver,String name) throws InterruptedException{
		
		swipeLocate(driver,"name", name , 20);
	}
	
	public String pageText(AppiumDriver<WebElement> driver,String target){
		
		WebElement dr = element(driver, target);
		Reporter.log("[Info] Page text has returned as : '" + dr.getText() + "' at " + SystemRelated.returnNowTime(timeFormat));
		return dr.getText();		
	}
	
	/**
	 * @purpose to swich the mode to webview
	 * @param driver
	 */
	public static void webViewMode(AppiumDriver<WebElement> driver){
		
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextNames);
			if (contextName.contains("WEBVIEW")) {
				driver.context(contextName);
				break;
			}
		}
	}
	
	/**
	 * @purpose to swich the mode to native
	 * @param driver
	 */
	public static void nativeMode(AppiumDriver<WebElement> driver){	
		
		driver.context("NATIVE_APP");
	}
	
	/**
	 * @purpose to rotate screen between : LANDSCAPE or PORTRAIT 
	 * @param driver
	 */
	public static void rotateScreen(AppiumDriver<WebElement> driver){	
		
		((AppiumDriver<WebElement>) driver).rotate(ScreenOrientation.LANDSCAPE);
	} 

	/**
	 * @purpose to swipe from one page to another
	 * @param driver
	 * @notice no need to care the screen size, it will calculate the current size of screen then give swipe action
	 */
	
    private static void swipeAction(AppiumDriver<WebElement> driver, String direction, WebElement element) {
    	
    	int width = driver.manage().window().getSize().width;	
        int height = driver.manage().window().getSize().height;
        
    	switch (direction) {    	
			case "up":
				JavascriptExecutor js = (JavascriptExecutor) driver;
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("direction", "up");
				scrollObject.put("element", ((RemoteWebElement) element).getId());
				js.executeScript("mobile: swipe", scrollObject);
				break;
			case "down":
				driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, 2000);break;
			case "left":
			//	driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, 6000);
				driver.swipe(width * 5 / 6, height / 2, width / 6, height / 2, 6000);
				LogUtil.info("[Info] Swipe left");
				break;
			case "right":
				driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, 2000);break;
			default:
				break;			
		}
   
	}
    
    private static void swipeAction(AppiumDriver<WebElement> driver, String direction) {
    	
    	int width = driver.manage().window().getSize().width;	
        int height = driver.manage().window().getSize().height;
        
    	switch (direction) {    	
			case "up":
				if (driver.getCapabilities().getCapability("platformName").toString().equalsIgnoreCase("Android")) {
					driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 2000);
					SysUtil.sleep(2);
				} else {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					HashMap<String, String> scrollObject = new HashMap<String, String>();
					scrollObject.put("direction", "up");
					js.executeScript("mobile: swipe", scrollObject);
				}
				break;
			case "down":
				driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, 2000);break;
			case "left":
			//	driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, 6000);
				driver.swipe(width * 5 / 6, height / 2, width / 6, height / 2, 6000);
				LogUtil.info("[Info] Swipe left");
				break;
			case "right":
				driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, 2000);break;
			default:
				break;			
		}
   
	}
    
    public void swipeAction(String direction) {
    
		
		if (androidDriver != null) {
			swipeAction(androidDriver,direction);
		}
		
		if (iOSDriver != null) {
			swipeAction(iOSDriver,direction);
		}
    }
    
    public void swipeAction(String direction, WebElement element) {
    
		
		if (androidDriver != null) {
			swipeAction(androidDriver,direction, element);
		}
		
		if (iOSDriver != null) {
			swipeAction(iOSDriver,direction, element);
		}
    }
  
    /**
     * @purpose to move from one element to another
     * @param driver
     * @param start
     * @param end
     */
    public void slide(AppiumDriver<WebElement> driver, String start, String end) {
    	
    	WebElement origin = element(driver, start);
    	WebElement destination = element(driver, end);    	
        TouchAction action = new TouchAction(driver);
        action.longPress(origin).moveTo(destination).release().perform();
    }
    
    /**
	 * @purpose to long press someone element for while
     * @param driver
     * @param target
     * @param duration
     */
    private void longPress(AppiumDriver<WebElement> driver,String target,int duration){
    	
      WebElement dr = element(driver, target);
  	  TouchAction action = new TouchAction(driver);  
  	 
  	//  action.longPress(dr).waitAction(duration * 1000).release().perform();
  	  LogUtil.info("[Info] LongPress at : " + target + " at " + SystemRelated.returnNowTime(timeFormat));
 	  
    }
    
    private void longPress(AppiumDriver<WebElement> driver,String target){
    	
    	longPress(driver,target,1);
    	
      }
    
    public void longPress(String target) {
   
		if (androidDriver != null) {
			longPress(androidDriver,target);
		}
		
		if (iOSDriver != null) {
			longPress(iOSDriver,target);
		}
    }
  
    
    private void longTap(AppiumDriver<WebElement> driver, String target , int duration){
    	WebElement dr = element(driver, target);
    //	driver.tap(1,dr,duration);
    	LogUtil.info("[Info] LongTap at : " + target + " at " + SystemRelated.returnNowTime(timeFormat)); 	
    }
    
    private void longTap(AppiumDriver<WebElement> driver, String target){
    	longTap(driver,target,2000);
    }
    
    public void longTap(String target){
		
		if (androidDriver != null) {
			longTap(androidDriver,target);
		}
		
		if (iOSDriver != null) {
			longTap(iOSDriver,target);
		}
    }
    
    public boolean verifyDisplay(AppiumDriver<WebElement> driver, String target, int timeout){
    	
		for(int i = 1; i <= 5; i++){
			try{
				WebElement dr = isDisplayed(driver, target, timeout);
				dr.isDisplayed();
				break;
			}
			catch(Exception e){
				LogUtil.warn("No element available to display, Retry (" + i + "/" + 5 + ") Times." );
				SysUtil.sleep(5);
				if(i == 5){
					return false;
				}
			}
		}
    	return true;
    }
    
    /*
	public boolean verifyDisplay(String target, int timeout) throws InterruptedException{
		
		if (SystemRelated.mobileType() == true){
			return verifyDisplay(androidDriver,target, timeout);
		}else{
			return verifyDisplay(iOSDriver,target, timeout);
		}
	}*/
		
    
    /*
	public static void takeScreenShot(AppiumDriver<WebElement> dr,String folderPath,String imageName){	
		
        try { 
            File srcFile = ((TakesScreenshot)dr). 
                    getScreenshotAs(OutputType.FILE); 
            FileUtils.copyFile 
            //(srcFile,new File("/" + folderPath + "/" + imageName + " -" + SystemRelated.returnNowTime("yyyyMMddHHmm") + ".png"));
            (srcFile,new File("/Users/james/Documents/work/Amway/Implement/Screenshot/test.png"));
        } catch (Exception e) { 
            e.printStackTrace(); 
        }   
    }  */
	
	/*
	 * @notice quit driver with time or can add other functions
	 */
    /*
	public static void quit(AppiumDriver<WebElement> driver) throws InterruptedException{
		
		driver.quit();
		Thread.sleep(3000);

		}
	*/
    /**
	 * @purpose to long press someone element for while
     * @param driver
     * @param target
     * @param duration
     */
	
	
	
		
	
}