package com.tpg.appium.learning.test.alert;


import com.tpg.appium.learning.BaseTest;
import com.tpg.appium.learning.pages.AlertViews;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfirmOkayAlertTest extends BaseTest {

    @Test
    public void testConfirmOkayAlert() {

        alertViews=home.clickAlertViewsPageTab(home.alertViewPage);
        alertViews.clickOnAlerts(AlertViews.alertViewsList, AlertViews.confirmCancelAlert);
        Assert.assertEquals(alertViews.isButtonDisplayed(alertViews.buttons, "Confirm"), true);
        alertViews.clickByButtonName(AlertViews.buttonType, "Confirm");
        alertViews.backToHome();
        Assert.assertEquals(home.getPageTitle(home.uiKitCatalogTitle), "UIKitCatalog");


    }


}

