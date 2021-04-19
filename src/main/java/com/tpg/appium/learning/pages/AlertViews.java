package com.tpg.appium.learning.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class AlertViews {

    AppiumDriver<MobileElement> driver;

    public AlertViews(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//XCUIElementTypeStaticText[@name='Simple']")
    public WebElement simple;

    @FindBy(name = "Alert Views")
    public WebElement alertPageTitle;

    @FindBy(name = "UIKitCatalog")
    public WebElement backToHome;

    @FindBy(xpath = "//XCUIElementTypeStaticText")
    public static List<WebElement> alertViewsList;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='OK']")
    public WebElement okButton;

    @FindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
    public WebElement cancelButton;

    @FindBy(xpath = "//XCUIElementTypeButton")
    public static List<WebElement> buttonType;

    public String buttons = "//XCUIElementTypeButton";

    public By buttonsConfirm = By.xpath("//XCUIElementTypeButton[@name='Confirm']");

    public String simpleAlert = "Simple";

    public String okayCancelAlert = "Okay / Cancel";

    public String otherAlert = "Other";

    public String textEntryAlert = "Text Entry";

    public String secureTextEntryAlert = "Secure Text Entry";

    public static String confirmCancelAlert = "Confirm / Cancel";

    public String destructiveAlert = "Destructive / Cancel";


    /**
     * @param list     List is the entire list of Web Element of Home Page
     * @param itemName Item Name is the name user needs to click on
     * @return Return the object of Home page
     */

    public AlertViews clickOnAlerts(List<WebElement> list, String itemName) {

        for (WebElement itemXpath : list) {
            if (itemXpath.getAttribute("value").equalsIgnoreCase(itemName)) {
                itemXpath.click();
                break;
            } else {
                System.out.println("The current item name is : " + itemXpath.getText() + " but do not found the expected item on the list : " + itemName);
            }

        }
        return this;
    }

    public AlertViews clickOk(WebElement locator) {
        locator.click();
        return this;
    }

    public AlertViews clickCancel(WebElement locator) {
        locator.click();
        return this;
    }

    public AlertViews clickByButtonName(List<WebElement> buttonList, String buttonName) {

        WebElement buttonPath=buttonList.stream().filter(buttonXpath -> buttonXpath.getText().equalsIgnoreCase(buttonName))
                .collect(Collectors.toList()).get(0);
        buttonPath.click();


      /* List<String> dd= buttonList.stream().map(buttonXpath -> buttonXpath.getText()).collect(Collectors.toList());
        if(dd.contains(buttonName)){

        }

        buttonList.stream().forEach(buttonXpath -> {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (buttonXpath.getText().equalsIgnoreCase(buttonName))
            {
                buttonXpath.click();
                break;
            }

        });
*/
        return this;

    }


    /**
     * @param buttonXpath xpath of the button
     * @param buttonName  button name to exists on screen
     * @return boolean
     */
    public boolean isButtonDisplayed(String buttonXpath, String buttonName) {
        String xpath = buttonXpath + "[@name='" + buttonName + "']";
        return driver.findElementByXPath(xpath).isDisplayed();


    }

    @FindBy(name = "UIKitCatalog")
    public WebElement backButton;

    public Home backToHome() {
        backButton.click();
        return new Home(driver);
    }
}
