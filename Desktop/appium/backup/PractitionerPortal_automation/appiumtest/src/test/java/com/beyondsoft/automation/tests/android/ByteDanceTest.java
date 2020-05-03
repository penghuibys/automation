
package com.beyondsoft.automation.tests.android;

import com.beyondsoft.automation.base.Setup;
import com.beyondsoft.automation.pages.android.ByteDance;
import frame.com.pp.auto.util.SysUtil;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ByteDanceTest extends Setup{

	
  @Test
  public void demo() throws InterruptedException, MalformedURLException {


//	  Date date = new Date();
////	  SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////	  String time = dfs.format(date);
////	  System.out.println(time);
//	  SysUtil.sleep(2);
//	  long between = (new Date().getTime() - date.getTime())/1000;

	  setUpAndroidDriver();

	  ByteDance byteDance = new ByteDance(androidDriver);
	  byteDance.demo();
  }
  
}











