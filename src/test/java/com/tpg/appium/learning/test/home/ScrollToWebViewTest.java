package com.tpg.appium.learning.test.home;

import com.tpg.appium.learning.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScrollToWebViewTest extends BaseTest {

    @Test
    public void testScrollToWebView() {

        webView=scrollAndClick(home.catalogListItems, "Web View");
        webView.backToHome();
        Assert.assertEquals(home.getPageTitle(home.uiKitCatalogTitle), "UIKitCatalog");

    }
}
