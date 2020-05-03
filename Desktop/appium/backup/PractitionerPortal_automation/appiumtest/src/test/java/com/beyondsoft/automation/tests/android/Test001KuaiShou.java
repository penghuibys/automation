
package com.beyondsoft.automation.tests.android;

import com.beyondsoft.automation.base.Setup;
import com.beyondsoft.automation.pages.android.Video;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


public class Test001KuaiShou extends Setup{

	
  @Test
  public void demo() throws InterruptedException, MalformedURLException {
	  setUpAndroidDriver_ks();

      Video video = new Video(androidDriver);
      video.demo();
  }
  
}











