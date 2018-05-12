package frame.com.mtf.ibm.operation;

import java.util.Set;
import java.util.function.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import frame.com.mtf.ibm.init.AndroidInit;
import frame.com.mtf.ibm.universal.ReadFile;
import frame.com.mtf.ibm.universal.SystemRelated;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.log.LogUtil;
import frame.com.pp.auto.util.SysUtil;


public class Locate extends AndroidInit {
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
	public Locate(){}
	
	private static ReadFile RF = new ReadFile();
	private static int timeout = 30;//second
	public static AppiumDriver<WebElement> androidDriver;
	public static AppiumDriver<WebElement> iosDriver;
	private static String timeFormat = "yyyy/MM/dd/ HH:mm:ss";
	
	/**
	 * @purpose due to network, device performance,other concerns, will do the next action when element is displayed 
	 * @param dr
	 * @param target
	 * @return after the element is showing then return the element for others action
	 */

	
	public static WebElement isDisplayed(AppiumDriver<WebElement> dr, String by, String target, int timeout){
		
		WebElement element = null;
		String attribute = null;
		String object = null;
		//for temporary element purpose		
		if (by != null){			
			object = target;
			attribute = by;
		} else {
			object = RF.getObject(target).get(0);
			attribute = RF.getObject(target).get(1);
//			object = target;
//			attribute = "xpath";
		}
		switch (attribute.toLowerCase()) {	
	    	case "id":
	    	//	new WebDriverWait(dr,timeout).until((Function<? super WebDriver, V>) ExpectedConditions.presenceOfElementLocated(By.id("")));
	    	//	until(ExpectedConditions.presenceOfElementLocated(By.id(object)));
	    		 	element = (WebElement) dr.findElement(By.id(object));
	    		break;
	    	case "name":
	    	//	new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.name(object)));
	    			element = (WebElement) dr.findElement(By.name(object));
	    		break;
	    	case "classname":
	    	//	new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.className(object)));
	    		element = dr.findElement(By.className(object));
	    		break;
	    	case "xpath":
	    	//	new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(object)));
	    		element =  dr.findElement(By.xpath(object));
	    		break;
	    	case "linktext":
	    	//	new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.linkText(object)));
	    		element = (WebElement) dr.findElement(By.linkText(object));
	    		break;
	    	case "tagname":
	    	//	new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.tagName(object)));
	    		element = (WebElement) dr.findElement(By.tagName(object));
	    		break;
	        case "css":
	    	//	new WebDriverWait(dr,timeout).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(object)));
	    		element = (WebElement) dr.findElement(By.cssSelector(object));
	    		break;      		
	        default : 
	        	System.out.println("[Info:] Your attribute " + attribute + " is not correct or empty for selenium,the API such as: id,name,etc");
	        	break;
			}
		return element;
	}
	
	//overloading for time out which can be adjustment for mobile only
	public static WebElement isDisplayed(AppiumDriver<WebElement> driver,String by,String target){
		
		return isDisplayed(driver,by,target,timeout);
	}
		
	public static WebElement isDisplayed(AppiumDriver<WebElement> driver,String target,int timeout){
		
		return isDisplayed(driver,null,target,timeout);
	}
	
	
	public static WebElement isDisplayed(String target){
		return isDisplayed(androidDriver,null,target,timeout);
	}
	
	public static WebElement isDisplayed(AppiumDriver<WebElement> driver,String target){
		
		return isDisplayed(driver,null,target,timeout);
	}
	
	private static void click(AppiumDriver<WebElement> driver,String target) throws InterruptedException{
		
		try{
			WebElement dr = isDisplayed(driver, target);
			dr.click();
			LogUtil.step("Clicked at '" + target + ", Passed");
		}
		catch(Exception e){
			FrameAssertion.fail("Clicked at '" + target + ", Failed");
		}
	}
	
	public static void click(String target) throws InterruptedException{
		
		if (SystemRelated.mobileType() == true){
			click(androidDriver,target);
		}else{
			click(IOSDriver,target);
		}
	}
		
	public static void send(AppiumDriver<WebElement> driver, String target, String content) throws InterruptedException{
		
/*		String log = "[Info] '" + content + "' has send to: " + target + " at " + SystemRelated.returnNowTime(timeFormat);
		WebElement dr = isDisplayed(driver, target);
		dr.sendKeys(content);
		Reporter.log(log);*/
		
		try{
			WebElement element = isDisplayed(driver, target);
			
			
		//	element.sendKeys(content);
			
		//	WebElement element = driver.findElement(By.xpath(target));
			element.sendKeys(content);
			LogUtil.step("Send Key '" + content + "' to " + target + ", Passed");
		}
		catch(Exception e){
			FrameAssertion.fail("Send Key '" + content + "' to " + target + ", Passed");
		}
	}
	
	public static void send(String target, String content) throws InterruptedException{
		
		if (SystemRelated.mobileType() == true){
			send(androidDriver,target,content);
		}else{
			send(IOSDriver,target,content);
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
	public static void swipeLocate(AppiumDriver<WebElement> driver, String by, String name, int swipeTime) throws InterruptedException{		
		int countTime = 0;
		while (true){
			try {
				WebElement dr = isDisplayed(driver, by, name, 1);
				//dr.click();
				LogUtil.info("[Info] Located at :" + name);
				break;
			} catch (Exception e) {
				countTime = countTime + 1;
		//		swipeAction("up");
				LogUtil.info("[Info] swiped and find again..." + countTime + " time(s)");
				
				if (countTime == swipeTime){
					LogUtil.info("[Info]swipe stoped, unable to find element or current page has issue.");
					break;
				}
			}			
		}
	}
		
	// post other By method, such as id , className, xpath
	public static void swipeLocate(AppiumDriver<WebElement> driver, String by,String name) throws InterruptedException{
		
		swipeLocate(driver , by, name, 20);
	}
	
	//By name , always to use as swipe locate, so create one method for it only
	public static void swipeLocate(AppiumDriver<WebElement> driver,String name, int swipeTime) throws InterruptedException{
		
		swipeLocate(driver,"name", name , swipeTime);
	}
	
	public static void swipeLocate(AppiumDriver<WebElement> driver,String name) throws InterruptedException{
		
		swipeLocate(driver,"name", name , 20);
	}
	
	public static String pageText(AppiumDriver<WebElement> driver,String target){
		
		WebElement dr = isDisplayed(driver, target);
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
	
//    private static void swipeAction(AppiumDriver<WebElement> driver, String direction) {
//    	
//    	int width = driver.manage().window().getSize().width;	
//        int height = driver.manage().window().getSize().height;
//        
//    	switch (direction) {    	
//			case "up":
//				driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, 2000);break;
//			case "down":
//				driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, 2000);break;
//			case "left":
//			//	driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, 6000);
//				driver.swipe(width * 5 / 6, height / 2, width / 6, height / 2, 6000);
//				LogUtil.info("[Info] Swipe left");
//				break;
//			case "right":
//				driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, 2000);break;
//			default:
//				break;			
//		}
//	}
//    
//    public static void swipeAction(String direction) {
//    	
//		if (SystemRelated.mobileType() == true){
//			swipeAction(androidDriver,direction);
//		}else{
//			swipeAction(IOSDriver,direction);
//		}
//    }
  
    /**
     * @purpose to move from one element to another
     * @param driver
     * @param start
     * @param end
     */
    public static void slide(AppiumDriver<WebElement> driver, String start, String end) {
    	
    	WebElement origin = isDisplayed(driver, start);
    	WebElement destination = isDisplayed(driver, end);    	
        TouchAction action = new TouchAction(driver);
        action.longPress(origin).moveTo(destination).release().perform();
    }
    
    /**
	 * @purpose to long press someone element for while
     * @param driver
     * @param target
     * @param duration
     */
    private static void longPress(AppiumDriver<WebElement> driver,String target,int duration){
    	
      WebElement dr = isDisplayed(driver, target);
  	  TouchAction action = new TouchAction(driver);  
  	 
  	//  action.longPress(dr).waitAction(duration * 1000).release().perform();
  	  LogUtil.info("[Info] LongPress at : " + target + " at " + SystemRelated.returnNowTime(timeFormat));
 	  
    }
    
    private static void longPress(AppiumDriver<WebElement> driver,String target){
    	
    	longPress(driver,target,1);
    	
      }
    
    public static void longPress(String target) {
    	
		if (SystemRelated.mobileType() == true) {
			longPress(androidDriver,target);
		} else {
			longPress(IOSDriver,target);
		}
    }
  
    
    private static void longTap(AppiumDriver<WebElement> driver, String target , int duration){
    	WebElement dr = isDisplayed(driver, target);
    //	driver.tap(1,dr,duration);
    	LogUtil.info("[Info] LongTap at : " + target + " at " + SystemRelated.returnNowTime(timeFormat)); 	
    }
    
    private static void longTap(AppiumDriver<WebElement> driver, String target){
    	longTap(driver,target,2000);
    }
    
    public static void longTap(String target){
    	
		if (SystemRelated.mobileType() == true) {
			longTap(androidDriver,target);
		} else {
			longTap(IOSDriver,target);
		}
    }
    
    public static boolean verifyDisplay(AppiumDriver<WebElement> driver, String target, int timeout){
    	
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
			return verifyDisplay(IOSDriver,target, timeout);
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