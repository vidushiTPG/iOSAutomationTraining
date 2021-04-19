package com.tpg.appium.learning.pages;

import com.tpg.appium.learning.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Slider extends BaseTest {

    public AppiumDriver<MobileElement> driver;

    public Slider (AppiumDriver<MobileElement> driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//XCUIElementTypePickerWheel")
    public List<WebElement> pickerWheelColumnList;

    public String getPageTile(WebElement locator) {
        return locator.getAttribute("value");

    }

    public boolean isPageTitleDisplayed(WebElement locator) {
        return locator.isDisplayed();
    }
}
