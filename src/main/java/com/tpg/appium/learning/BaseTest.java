package com.tpg.appium.learning;

import com.tpg.appium.learning.pages.AlertViews;
import com.tpg.appium.learning.pages.Home;
import com.tpg.appium.learning.pages.PickerView;
import com.tpg.appium.learning.pages.WebView;
import com.tpg.appium.learning.utility.AppiumServerUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    public static AppiumDriver<MobileElement> driver;
    protected Properties props;
    protected Map<String, String> strings;
    protected String deviceName;
    protected String platformName;
    protected String platformVersion;
    protected Home home;
    protected AlertViews alertViews;
    protected PickerView pickerView;
    protected WebView webView;


    public AppiumDriver getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    @BeforeSuite
    public void beforeSuite() {
        AppiumServerUtility.start();
    }

    @BeforeMethod
    public void beforeTest() throws Exception {

        URL url;
        AppiumDriver driver;
        InputStream inputStream = null;
        try {
            Properties props = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            setProps(props);

            //App Platform and Device Name
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, props.getProperty("deviceName"));

            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, props.getProperty("platformName"));
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, props.getProperty("platformVersion"));
            url = new URL(props.getProperty("appiumURL"));

            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            desiredCapabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, props.getProperty("launch_timeOut"));
            desiredCapabilities.setCapability("commandTimeouts", "120000");
            desiredCapabilities.setCapability("newCommandTimeouts", "0");

            String iOSAppUrl = getClass().getResource(props.getProperty("iOSAppLocation")).getFile();
            System.out.println("appUrl is" + iOSAppUrl);

            desiredCapabilities.setCapability(MobileCapabilityType.APP, iOSAppUrl);
            driver = new IOSDriver(url, desiredCapabilities);
            setDriver(driver);
            home = new Home(driver);

        } catch (Exception e) {
            System.out.println("driver initialization failure. ABORT!!!\n" + e.toString());
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    @AfterMethod
    public void afterTest() {
        getDriver().quit();

    }

    @AfterSuite
    public void afterSuite() {
        AppiumServerUtility.stop();
    }

    /**
     * Restart the app after every test class to go back to the main
     * screen and to reset the behavior
     */


    //some common locators

    @FindBy(name = "UIKitCatalog")
    public WebElement backButton;

    public Home backToHome() {
        backButton.click();
        return new Home(driver);
    }


    public void scrollDown() {
        Dimension dimension = getDriver().manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5); //scroll start from get  50% height of screen
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction((PerformsTouchActions) getDriver())
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(0, scrollEnd))
                .release().perform();
    }

    public WebView scrollAndClick(By listItems, String textToClick) {
        boolean flag = false;

        while (true) {
            for (WebElement elementToClick : driver.findElements(listItems)) {
                if (elementToClick.getAttribute("value").equals(textToClick)) {
                    elementToClick.click();
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
            else scrollDown();
        }
        return new WebView(driver);

    }


}
