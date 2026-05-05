package org.booking.testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_10_HotelOptionIsSelected extends BaseTest {

    @Test
    public void verifyHotelsOptionIsSelected() {

        Log.info("===== TEST STARTED: Verify Hotels Option Selection =====");

        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);

        Log.info("Closing popup if present");
        hp.closePop();

        Log.info("Entering city: Nairobi");
        hp.searchCity("Nairobi");

        Log.info("Selecting travel dates");
        hp.startDate("30", "May", "2026");
        hp.endDate("5", "June", "2026");

        Log.info("Selecting number of adults");
        hp.enterNumberOfAdults(4);

        Log.info("Clicking Search button");
        hp.search();

        Log.info("Attempting to select 'Hotels' filter option");
        hsp.clickHotelsOption();

        //  Hard assertion with log
        Assert.assertTrue(
                hsp.checkHotelsOption.isEnabled(),
                " Hotels option is NOT enabled or could not be selected"
        );
        Log.info(" Hotels option is enabled and successfully selected");
        Log.info("===== TEST COMPLETED: Verify Hotels Option Selection =====");
        ScreenshotUtil.takeScreenshot(driver, "TC_10_HotelOptionIsSelected");
    }
}