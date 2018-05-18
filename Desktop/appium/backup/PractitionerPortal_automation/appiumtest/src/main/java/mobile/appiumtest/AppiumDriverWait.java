package mobile.appiumtest;

import io.appium.java_client.AppiumDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;



public class AppiumDriverWait extends FluentWait<AppiumDriver> {

    //默认轮询时间(毫秒)
    public final static long DEFAULT_POLLINGEVERY_TIMEMILLS = Integer.parseInt("20000");
    public final static long DEFAULT_TIMEOUT_INSECONDS = Integer.parseInt("60");
    /**
       * Wait will ignore instances of NotFoundException that are encountered (thrown) by default in
       * the 'until' condition, and immediately propagate all others.  You can add more to the ignore
       * list by calling ignoring(exceptions to add).
       *
       * @param driver The AppiumDriver instance to pass to the expected conditions
       * @see WebDriverWait#ignoring(java.lang.Class)
       */
    public AppiumDriverWait(AppiumDriver driver) {
        this(driver, new SystemClock(), Sleeper.SYSTEM_SLEEPER, DEFAULT_TIMEOUT_INSECONDS, DEFAULT_POLLINGEVERY_TIMEMILLS);
    }
    /**
       * Wait will ignore instances of NotFoundException that are encountered (thrown) by default in
       * the 'until' condition, and immediately propagate all others.  You can add more to the ignore
       * list by calling ignoring(exceptions to add).
       *
       * @param driver The AppiumDriver instance to pass to the expected conditions
       * @param timeOutInSeconds The timeout in seconds when an expectation is called
       * @see WebDriverWait#ignoring(java.lang.Class)
       */
    public AppiumDriverWait(AppiumDriver driver, long timeOutInSeconds) {
        this(driver, new SystemClock(), Sleeper.SYSTEM_SLEEPER, timeOutInSeconds, DEFAULT_POLLINGEVERY_TIMEMILLS);
    }
    /**
       * Wait will ignore instances of NotFoundException that are encountered (thrown) by default in
       * the 'until' condition, and immediately propagate all others.  You can add more to the ignore
       * list by calling ignoring(exceptions to add).
       *
       * @param driver The WebDriver instance to pass to the expected conditions
       * @param timeOutInSeconds The timeout in seconds when an expectation is called
       * @param sleepInMillis The duration in milliseconds to sleep between polls.
       * @see WebDriverWait#ignoring(java.lang.Class)
       */
    public AppiumDriverWait(AppiumDriver driver, long timeOutInSeconds, long sleepInMillis) {
        this(driver, new SystemClock(), Sleeper.SYSTEM_SLEEPER, timeOutInSeconds, sleepInMillis);
      }
    protected AppiumDriverWait(AppiumDriver driver, Clock clock, Sleeper sleeper, long timeOutInSeconds,
          long sleepTimeOut) {
        super(driver, clock, sleeper);
        withTimeout(timeOutInSeconds, TimeUnit.SECONDS);
        pollingEvery(sleepTimeOut, TimeUnit.MILLISECONDS);
        ignoring(NotFoundException.class);
    }
    
    public interface AppiumExpectedCondition<T> extends Function<AppiumDriver,T>{

    }


}
