package com.tpg.appium.learning.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Home {

    protected AppiumDriver<MobileElement> driver;

    public Home(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!uiKitCatalogTitle.isDisplayed()){
            throw new IllegalStateException("Home Page Not Found");
        }
    }


    @FindBy(name = "UIKitCatalog")
    public WebElement uiKitCatalogTitle;

    @FindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[@name='Activity Indicators']")
    public WebElement activityIndicators;

    @FindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[@name='Alert Views']")
    public WebElement alertViews;

    @FindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText")
    public List<WebElement> catalogList;

    public By catalogListItems = By.xpath("//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText");

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Alert Views']")
    public WebElement alertViewTitle;

    public String alertViewPage = "Alert Views";

    public String datePickerPage = "Date Picker";

    public String pickerViewsPage = "Picker View";

    /**
     * @param elementXpath is the Web Element
     * @return it return the value of the Web element
     */
    public String getPageTitle(WebElement elementXpath) {

        return elementXpath.getAttribute("name");
    }

    /**
     * @param itemName Item Name is the name user needs to click on
     * @return Return the object of Home page
     */

    public WebElement ifItemPresent(String itemName) {
        Optional<WebElement> webElementOptional = catalogList.stream().filter(itemXpath -> itemXpath.getText().equalsIgnoreCase(itemName))
                .collect(Collectors.toSet()).stream().findFirst();
        return webElementOptional.get();
    }

    public AlertViews clickAlertViewsPageTab(String itemName) {
        WebElement webElement = ifItemPresent(itemName);
        webElement.click();
        return new AlertViews(driver);
    }

    public PickerView clickPickerViewPageTab(String itemName) {
        WebElement webElement = ifItemPresent(itemName);
        webElement.click();
        return new PickerView(driver);
    }


    public AlertViews openAlertPage() {
        alertViewTitle.click();
        return new AlertViews(driver);
    }


}
