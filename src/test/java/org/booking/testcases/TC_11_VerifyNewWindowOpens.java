package org.booking.testcases;
import basetest.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import org.booking.pages.PropertyDetailsPage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_11_VerifyNewWindowOpens extends BaseTest {

    @Test
    public void verifySeeAvailabilityClickWorks() {

        SoftAssert softAssert = new SoftAssert();
        boolean isPropertyDetailsOpened = true;

        Log.info("===== TEST STARTED: Verify See Availability Opens Property Details =====");

        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);
        try {
            Log.info("Closing popup if present");
            hp.closePop();

            Log.info("Entering city: Nairobi");
            hp.searchCity("Nairobi");

            Log.info("Selecting travel dates");
            hp.startDate("30", "May", "2026");
            hp.endDate("5", "June", "2026");

            Log.info("Selecting number of adults");
            hp.enterNumberOfAdults(4);

            Log.info("Clicking search button");
            hp.search();

            Log.info("Applying Hotels filter");
            hsp.clickHotelsOption();

            Log.info("Applying Vacation Homes filter");
            hsp.clickVacationHomesOption();

            Log.info("Applying Wonderful filter");
            hsp.clickWonderfulOption();

            Log.info("Applying Smart Filter: Elevator");
            hsp.enterSmartFilter("Elevator");

            Log.info("Clicking 'See Availability' and opening property details");
            pdp.extractHolidayHomeDetails();

            Log.info(" See Availability click worked and property details were opened");
        } catch (Exception e) {
            isPropertyDetailsOpened = false;
            Log.error(" Failed while clicking See Availability or opening property details", e);
        }
        //  Soft assertion based on EXECUTION SUCCESS
        softAssert.assertTrue(
                isPropertyDetailsOpened,
                "See Availability click failed or property details did not open"
        );
        Log.info("===== TEST COMPLETED: Verify See Availability Opens Property Details =====");
        softAssert.assertAll();
        ScreenshotUtil.takeScreenshot(driver, "TC_11_VerifyNewWindowOpens");
    }
}