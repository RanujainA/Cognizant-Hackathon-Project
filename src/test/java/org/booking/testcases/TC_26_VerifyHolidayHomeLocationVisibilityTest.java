package org.booking.testcases;

import basetest.BaseTest;
import org.booking.pages.HomePage;
import org.booking.pages.PropertyDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_26_VerifyHolidayHomeLocationVisibilityTest extends BaseTest {

    @Test
    public void verifyHolidayHomeLocationForFirstFiveHomes() {

        Log.info("TEST STARTED : Verify Holiday Home Location Visibility");
        Log.info("Test Case: Verify that the Holiday Home location is displayed for first five homes");

        // Initialize Home Page
        Log.info("Initializing Home Page");
        HomePage hp = new HomePage(driver);

        // Close pop-up if present
        Log.info("Attempting to close pop-up if present");
        hp.closePop();

        // Enter search criteria
        Log.info("Entering search city: Nairobi");
        hp.searchCity("Nairobi");

        Log.info("Selecting check-in date: 30 May 2026");
        hp.startDate("30", "May", "2026");

        Log.info("Selecting check-out date: 30 June 2026");
        hp.endDate("30", "June", "2026");

        Log.info("Setting number of adults to 4");
        hp.enterNumberOfAdults(4);

        Log.info("Clicking Search button");
        hp.search();

        // Initialize Property Details Page
        Log.info("Initializing Property Details Page");
        PropertyDetailsPage propertyDetailsPage =
                new PropertyDetailsPage(driver);

        // Verify location visibility for first five holiday homes
        Log.info("Verifying location visibility for the first five holiday homes");
        boolean isLocationDisplayed =
                propertyDetailsPage.verifyLocationDisplayedForFirstFiveHolidayHomes();

        // Assertion
        Assert.assertTrue(
                isLocationDisplayed,
                "Location is not displayed for one or more of the first five holiday homes"
        );
        Log.info("Location displayed for all first five holiday homes");
        Log.info("TEST COMPLETED SUCCESSFULLY");
        ScreenshotUtil.takeScreenshot(driver, "TC_26_VerifyHolidayHomeLocationVisibilityTest");
    }
}