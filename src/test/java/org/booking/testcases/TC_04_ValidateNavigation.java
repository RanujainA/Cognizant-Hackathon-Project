package org.booking.testcases;
// Test Case: Verify navigation of driver happening properly or not.
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import org.booking.pages.PropertyDetailsPage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_04_ValidateNavigation extends BaseTest {
    @Test
    public void verifyNavigationOfDriver(){
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);

        Log.info("Starting test: Validate driver navigation");

        hp.closePop();
        Log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        Log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        Log.info("Start date selected: " + startDateSelected);

        hp.endDate("30", "June", "2026");
        Log.info("End date selected: 30 June 2026");

        hp.enterNumberOfAdults(4);
        Log.info("Entered number of adults: 4");

        hp.search();
        Log.info("Clicked search button");

        hsp.clickVacationHomesOption();
        Log.info("Clicked Vacation Homes filter");

        hsp.clickHotelsOption();
        Log.info("Clicked Hotels filter");

        hsp.clickWonderfulOption();
        Log.info("Clicked Wonderful filter");

        hsp.enterSmartFilter("Elevator");
        Log.info("Entered Elevator in Smart Filters");

        pdp.extractHolidayHomeDetails();
        Log.info("Extracted holiday home details");

        Assert.assertTrue(hsp.checkPropertiesPageUrl(), "Invalid Navigation");
        Log.info("Assertion completed: Navigation to property details page verified");
        ScreenshotUtil.takeScreenshot(driver, "TC_04_ValidateNavigation");
    }
}
