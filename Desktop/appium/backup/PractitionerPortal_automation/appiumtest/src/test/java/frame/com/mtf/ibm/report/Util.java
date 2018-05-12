package frame.com.mtf.ibm.report;

import org.testng.Assert;
import org.testng.Reporter;

import frame.com.mtf.ibm.universal.SystemRelated;

public class Util {
	
	private static String timeFormat = "yyyy/MM/dd/ HH:mm:ss";
    /**
	 * @purpose to verify the result with log into testNG report
     * @param actual
     * @param expected
     */
	
	public static void assertEquals(String actual,String expected){
		
		Reporter.log("[Assert]: get actual result is : '" + actual + "' get expected result is : '" + expected + 
				"' at " + SystemRelated.returnNowTime(timeFormat));
		
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * @purpose to log the print out information into testNG report
	 * @param log
	 */
	public static void Log(String log){
		Reporter.log("[Info]" + log + " at " + SystemRelated.returnNowTime(timeFormat));
	}
}
