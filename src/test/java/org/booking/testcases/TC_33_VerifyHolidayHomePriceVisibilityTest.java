package org.booking.testcases;

import basetest.BaseTest;
import org.booking.pages.HomePage;
import org.booking.pages.PropertyDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;

public class TC_33_VerifyHolidayHomePriceVisibilityTest extends BaseTest {

    @Test
    public void run() {

        Log.info("TEST STARTED : Verify Holiday Home Price Visibility");

        // Initialize Home Page
        Log.info("Initializing Home Page");
        HomePage hp = new HomePage(driver);

        // Close popup if present
        Log.info("Attempting to close pop-up if present");
        hp.closePop();

        // Enter search details
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
        PropertyDetailsPage page = new PropertyDetailsPage(driver);

        // Verify price visibility for first five holiday homes
        Log.info("Verifying that price is displayed for the first five holiday homes");
        boolean isPriceDisplayed =
                page.verifyPriceIsDisplayedForFirstFiveHomes();

        // Assertion
        Assert.assertTrue(
                isPriceDisplayed,
                "Price is not displayed for one or more holiday homes"
        );

        Log.info("Price is displayed for all first five holiday homes");
        Log.info("TEST COMPLETED SUCCESSFULLY");
    }
}