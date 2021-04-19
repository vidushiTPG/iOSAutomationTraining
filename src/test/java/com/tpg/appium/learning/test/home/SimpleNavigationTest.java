package com.tpg.appium.learning.test.home;


import com.tpg.appium.learning.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleNavigationTest extends BaseTest {


    //simple Navigation test thats clicks on a items and verify the title of landing page
    @Test
    public void testSimpleNavigation() {


        alertViews = home.clickAlertViewsPageTab(home.alertViewPage);
        String pageTitle = home.getPageTitle(alertViews.alertPageTitle);
        Assert.assertTrue(pageTitle.equalsIgnoreCase(home.alertViewPage));
        alertViews.backToHome();
        Assert.assertEquals(home.getPageTitle(home.uiKitCatalogTitle), "UIKitCatalog");


    }


}
