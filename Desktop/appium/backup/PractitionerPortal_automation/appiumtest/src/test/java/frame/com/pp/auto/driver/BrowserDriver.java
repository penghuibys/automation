package frame.com.pp.auto.driver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import frame.com.pp.auto.config.FrameConfig;
import frame.com.pp.auto.log.LogUtil;
import frame.com.pp.auto.util.FileUtil;
import frame.com.pp.auto.util.SysUtil;

public class BrowserDriver {
	
	private WebDriver driver = null;
	private static int RETRY_TIME;
	private static int RETRY_WAIT;
	
	public BrowserDriver(){
		RETRY_TIME = Integer.parseInt(FrameConfig.getInstance().getConfig("RetryTime"));
		RETRY_WAIT = Integer.parseInt(FrameConfig.getInstance().getConfig("RetryWait"));
	}
	
	protected WebDriver chromeDriver() throws Exception {
		LogUtil.frameLog("Launch Chrome Browser...");
		System.out.println(FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("ChromeDriverPath")));
		System.setProperty("webdriver.chrome.driver", FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("ChromeDriverPath")));
		
		
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Long.parseLong(FrameConfig.getInstance().getConfig("TimeOut")), TimeUnit.SECONDS);
				break;
			}
			catch(Exception e){
				LogUtil.warn("Launch Chrome Browser Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return driver;
	}

	protected WebDriver fireFoxDriver() {
		LogUtil.frameLog("Launch Firefox Browser...");
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				FirefoxProfile profile = new FirefoxProfile(); 
		        profile.setPreference("browser.download.folderList", 2);
		        profile.setPreference("browser.download.dir", "C:\\Downloads");
			//	driver = new FirefoxDriver(profile);
				driver.manage().timeouts().implicitlyWait(Long.parseLong(FrameConfig.getInstance().getConfig("TimeOut")), TimeUnit.SECONDS);
				driver.manage().window().maximize();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Launch Firefox Browser Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return driver;
	}

	protected WebDriver safariDriver() {
		LogUtil.frameLog("Launch Safari Browser...");
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				driver = new SafariDriver();
				driver.manage().timeouts().implicitlyWait(Long.parseLong(FrameConfig.getInstance().getConfig("TimeOut")), TimeUnit.SECONDS);
				break;
			}
			catch(Exception e){
				LogUtil.warn("Launch Safari Browser Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return driver;
	}
	
	protected WebDriver ieDriver() {
		LogUtil.frameLog("Launch IE Browser...");
		System.setProperty("webdriver.ie.driver", FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("IEDriverPath")));
		for(int i = 1; i <= RETRY_TIME; i++){
			try{
				driver = new InternetExplorerDriver();
				driver.manage().timeouts().implicitlyWait(Long.parseLong(FrameConfig.getInstance().getConfig("TimeOut")), TimeUnit.SECONDS);
				driver.manage().window().maximize();
				break;
			}
			catch(Exception e){
				LogUtil.warn("Launch IE Browser Failed, Retry (" + i + "/" + RETRY_TIME + ") Times After " + RETRY_WAIT + "Seconds");
				SysUtil.sleep(RETRY_WAIT);
				if(i == RETRY_TIME){
					throw e;
				}
			}
		}
		return driver;
	}

}
