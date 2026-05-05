package org.booking.testcases;
import basetest.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import org.booking.pages.PropertyDetailsPage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_06_VerifyHolidayHomesResults extends BaseTest {
    @Test
    public void verifyHolidayHomesResults(){
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage bd = new PropertyDetailsPage(driver);

        Log.info("Starting test: Verify Holiday Homes results");

        hp.closePop();
        Log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        Log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        Log.info("Start date selected: " + startDateSelected);

        hp.endDate("7", "May", "2026");
        Log.info("End date selected: 7 May 2026");

        hp.enterNumberOfAdults(2);
        Log.info("Entered number of adults: 2");

        hp.search();
        Log.info("Clicked search button");

        hsp.clickVacationHomesOption();
        Log.info("Clicked Vacation Homes filter");

        SoftAssert sa = new SoftAssert();
        boolean propertiesDisplayed = bd.arePropertiesDisplayed();
        Log.info("Properties displayed: " + propertiesDisplayed);

        sa.assertTrue(propertiesDisplayed, "No properties displayed");

        boolean propertiesContainCity = bd.doPropertiesContainCity("Nairobi");
        Log.info("Properties contain city Nairobi: " + propertiesContainCity);

        sa.assertTrue(propertiesContainCity, "Holiday homes in Nairobi are not displayed for given dates");

        sa.assertAll();
        Log.info("Soft assertions executed");
        ScreenshotUtil.takeScreenshot(driver, "TC_06_VerifyHolidayHomesResults");
    }
}
