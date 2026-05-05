package org.booking.testcases;
// Test Case: Invalid Input in Smart Filters
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_03_InvalidInputInSmartFilters extends BaseTest {
    @Test
    public void enterSmartFilter(){
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);

        Log.info("Starting test: Invalid input in Smart Filters");

        hp.closePop();
        Log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        Log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        Log.info("Start date selected: " + startDateSelected);

        hp.endDate("7", "May", "2026");
        Log.info("End date selected: 7 May 2026");

        hp.enterNumberOfAdults(4);
        Log.info("Entered number of adults: 4");

        hp.search();
        Log.info("Clicked search button");

        hsp.enterSmartFilter("XYZ");
        Log.info("Entered invalid Smart Filter value: XYZ");

        boolean filterApplied = hsp.isSmartFilterApplied();
        Log.info("Smart filter applied status: " + filterApplied);

        Assert.assertFalse(filterApplied, "Smart Filter should not be applied for invalid input");
        Log.info("Assertion completed: Invalid Smart Filter was not applied");
        ScreenshotUtil.takeScreenshot(driver, "TC_03_InvalidInputInSmartFilters");
    }
}
