package com.tpg.appium.learning.pages;

import com.tpg.appium.learning.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PickerView extends BaseTest {

    public AppiumDriver<MobileElement> driver;

    public PickerView (AppiumDriver<MobileElement> driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//XCUIElementTypePickerWheel")
    public List<WebElement> pickerWheelColumnList;

    @FindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Picker View']")
    public WebElement pickerViewTitle;

    @FindBy(name = "UIKitCatalog")
    public WebElement backButton;


    public String getPageTile(WebElement locator) {
        return locator.getAttribute("value");

    }

    public boolean isPageTitleDisplayed(WebElement locator) {
        return locator.isDisplayed();

    }

    public String getFirstColumn() {
        String firstColumnValue = pickerWheelColumnList.get(0).getAttribute("value");
        return firstColumnValue;
    }

    public String getSecondColumn() {
        String secondColumnValue = pickerWheelColumnList.get(1).getAttribute("value");
        return secondColumnValue;
    }

    public String getThirdColumn() {
        String thirdColumnValue = pickerWheelColumnList.get(2).getAttribute("value");
        return thirdColumnValue;
    }

    public PickerView pickerViewSendKeys(String firstColumnValue, String secondColumnValue, String thirdColumnValue) throws InterruptedException {
        Thread.sleep(5000);
        pickerWheelColumnList.get(0).sendKeys(firstColumnValue);
        Thread.sleep(5000);
        pickerWheelColumnList.get(1).sendKeys(secondColumnValue);
        Thread.sleep(5000);
        pickerWheelColumnList.get(2).sendKeys(thirdColumnValue);
        Thread.sleep(5000);
        return this;
    }

    public Home backToHome() {
        backButton.click();
        return new Home(driver);
    }
}

