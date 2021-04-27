package com.tpg.appium.learning.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebView {

    AppiumDriver<MobileElement> driver;

    public WebView(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!webViewPageTitle.isDisplayed()){
            throw new IllegalStateException("Web View Page not found");
        }
    }

    @FindBy(name = "UIKitCatalog")
    public WebElement backButton;

    @FindBy(name = "Web View")
    public WebElement webViewPageTitle;

    public Home backToHome() {
        backButton.click();
        return new Home(driver);
    }
}
