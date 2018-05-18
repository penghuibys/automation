
package com.beyondsoft.automation.pages.ios;

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
	
  
	public String getValidationInfo(String target) throws InterruptedException{
		Locate locate = new Locate(iOSDriver, null);
		WebElement element = locate.element(iOSDriver, target);
		return element.getAttribute("name");
	}
	
	public List<String> getAllValidationInfo(String target) throws InterruptedException{
		Locate locate = new Locate(iOSDriver, null);
		List<WebElement> elements = locate.elements(iOSDriver, target);
		List<String> texts = new ArrayList<>();
		for (int i = 0; i < elements.size(); i++) {
			String name = elements.get(i).getAttribute("name");
			texts.add(name);
		}
		return texts;
	}
	
	public String getValidationValue(String target) throws InterruptedException{
		Locate locate = new Locate(iOSDriver, null);
		WebElement element = locate.element(iOSDriver, target);
		return element.getAttribute("value");
	}
  
  
}











