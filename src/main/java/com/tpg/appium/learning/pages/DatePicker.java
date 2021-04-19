package com.tpg.appium.learning.pages;

import com.tpg.appium.learning.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatePicker extends BaseTest {


    public AppiumDriver<MobileElement> driver;

    public DatePicker (AppiumDriver<MobileElement> driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Date Picker']")
    public WebElement datePickerTitle;

    @FindBy(xpath = "//XCUIElementTypeOther[@name='Date and Time Picker']")
    public WebElement datePickerField;

    @iOSXCUITFindBy(accessibility = "13")
    public WebElement datePicker;

    public MobileBy.ByAccessibilityId yearPicker = (MobileBy.ByAccessibilityId) MobileBy.AccessibilityId("Show year picker");


    public boolean isPageTitleDisplayed(WebElement locator) {
        return locator.isDisplayed();

    }

    public boolean isDatePickerFieldDisplayed(WebElement locator) {
        return locator.isDisplayed();

    }

    public String getPageTile(WebElement locator) {
        return locator.getText();

    }

    //Step 1 click on date Field to open the Calender
    public DatePicker openCalender() {
        datePickerField.click();
        return this;
    }

    //Step 2 Click on date from Calender
    public DatePicker clickDateCalendar() {
        datePicker.click();
        return this;
    }

    //Step 3 Click on Month & Year dropdown to open wheel picker
    public DatePicker clickYearCalender() {
        driver.findElement(yearPicker).click();
        return this;
    }


}
