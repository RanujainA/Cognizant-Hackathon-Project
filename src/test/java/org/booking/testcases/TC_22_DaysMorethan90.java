package org.booking.testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.booking.pages.HomePage;
import utilities.ScreenshotUtil;

public class TC_22_DaysMorethan90 extends BaseTest {

    @Test
    public void validateSearchButton(){
        HomePage hp = new HomePage(driver);
        hp.closePop();
        hp.searchCity("Nairobi");
        Assert.assertTrue(hp.startDate("30", "May", "2026"));
        hp.endDate("30", "September", "2026");
        hp.enterNumberOfAdults(2);
        hp.search();

        SoftAssert sa = new SoftAssert();
        sa.assertFalse(driver.getCurrentUrl().contains("searchresults"), "Search results are displayed after 90 days");
        sa.assertAll();
        ScreenshotUtil.takeScreenshot(driver, "TC_22_DaysMorethan90");
    }
}
