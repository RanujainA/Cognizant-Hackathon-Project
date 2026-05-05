package org.booking.testcases;

import basetest.BaseTest;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_25_VerifySmartFilterInputAcceptanceTest extends BaseTest {
    @Test
    public void run(){

        Log.info("TEST STARTED : Verify Smart Filter Input Acceptance");
        Log.info("Initializing Home Page");
        HomePage hp = new HomePage(driver);
        Log.info("Closing popup if present");
        hp.closePop();
        Log.info("Entering city: Nairobi");
        hp.searchCity("Nairobi");
        Log.info("Selecting start date: 30 May 2026");
        hp.startDate("30", "May", "2026");
        Log.info("Selecting end date: 30 June 2026");
        hp.endDate("30", "June", "2026");
        Log.info("Setting number of adults to 4");
        hp.enterNumberOfAdults(4);
        Log.info("Clicking Search button");
        hp.search();
        Log.info("Initializing Hotel Search Page");
        HotelSearchPage hsp = new HotelSearchPage(driver);
        Log.info("Entering Smart Filter value: Elevator");
        hsp.enterSmartFilter("Elevator");
        String expectedFilter = "Elevator";
        Log.info("Fetching entered Smart Filter text");
        String actualFilter = hsp.getSmartFilterText();

        Log.info("Expected Smart Filter value: " + expectedFilter);
        Log.info("Actual Smart Filter value: " + actualFilter);

        Assert.assertEquals(actualFilter,expectedFilter,"Smart Filter text area did not accept the input");
        Log.info("Smart Filter text area accepted input correctly");
        Log.info("TEST COMPLETED SUCCESSFULLY");
        ScreenshotUtil.takeScreenshot(driver, "TC_25_VerifySmartFilterInputAcceptanceTest");
    }
}
