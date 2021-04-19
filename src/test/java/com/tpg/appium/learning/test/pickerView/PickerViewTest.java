package com.tpg.appium.learning.test.pickerView;

import com.tpg.appium.learning.BaseTest;

import com.tpg.appium.learning.pages.Home;
import com.tpg.appium.learning.pages.PickerView;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PickerViewTest extends BaseTest {

    @Test
    public void testPickerView() throws InterruptedException {


        pickerView=home.clickPickerViewPageTab(home.pickerViewsPage);
        Assert.assertEquals(pickerView.isPageTitleDisplayed(pickerView.pickerViewTitle),true);

        System.out.println(pickerView.getFirstColumn());

        System.out.println(pickerView.getSecondColumn());

        System.out.println(pickerView.getThirdColumn());


        pickerView.pickerViewSendKeys("90", "100", "155");
        Assert.assertEquals(pickerView.getFirstColumn(),"90");
        Assert.assertEquals(pickerView.getSecondColumn(),"100");
        Assert.assertEquals(pickerView.getThirdColumn(),"155");
        pickerView.backToHome();
        Assert.assertEquals(home.getPageTitle(home.uiKitCatalogTitle),"UIKitCatalog");





    }
}
