
package com.beyondsoft.automation.basevalidation;

import io.appium.java_client.AppiumDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import frame.com.mtf.ibm.operation.Locate;

public class Base {
	public Base(AppiumDriver<WebElement> iOSDriver, AppiumDriver<WebElement> androidDriver){
		Base.iOSDriver = iOSDriver;
		Base.androidDriver = androidDriver;
	}
	
	public static AppiumDriver<WebElement> iOSDriver;
	public static AppiumDriver<WebElement> androidDriver;
	public static Locate locate;
	public static WebElement element;
	
  
	public String getValidationInfo(String target) throws InterruptedException{
		if (androidDriver != null) {
			locate = new Locate(null, androidDriver);
			element = locate.element(androidDriver, target);
		} else {
			locate = new Locate(iOSDriver, null);
			element = locate.element(iOSDriver, target);
		}
		
		return element.getAttribute("name");
	}
	
	public List<String> getAllValidationInfo(String target) throws InterruptedException{
		List<WebElement> elements;
		if (androidDriver != null) {
			locate = new Locate(null, androidDriver);
			elements = locate.elements(androidDriver, target);
		} else {
			locate = new Locate(iOSDriver, null);
			elements = locate.elements(iOSDriver, target);
		}
		
		List<String> texts = new ArrayList<>();
		for (int i = 0; i < elements.size(); i++) {
			String name = elements.get(i).getAttribute("name");
			texts.add(name);
		}
		return texts;
	}
	
	public String getValidationValue(String target) throws InterruptedException{
		if (androidDriver != null) {
			locate = new Locate(null, androidDriver);
			element = locate.element(androidDriver, target);
		} else {
			locate = new Locate(iOSDriver, null);
			element = locate.element(iOSDriver, target);
		}
		return element.getAttribute("value");
	}
  
  
}











