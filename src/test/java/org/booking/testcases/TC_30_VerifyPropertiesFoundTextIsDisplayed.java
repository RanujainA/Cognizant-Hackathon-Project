package org.booking.testcases;

import basetest.BaseTest;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_30_VerifyPropertiesFoundTextIsDisplayed extends BaseTest {

    @Test
    public void run() {
        Log.info("TEST STARTED : Verify Properties Found Text Visibility");

        // Initialize Home Page
        Log.info("Initializing Home Page");
        HomePage hp = new HomePage(driver);

        // Initialize Search Results Page
        Log.info("Initializing Hotel Search Page");
        HotelSearchPage hsp = new HotelSearchPage(driver);

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

        // Fetch and log results text
        Log.info("Fetching 'properties found' text from search results");
        String resultsText = hsp.getPropertiesFoundText();
        Log.info("Search results text displayed: " + resultsText);

        // Assertion
        Assert.assertTrue(
                hsp.isPropertiesFoundTextDisplayed(),
                "Properties found text is not displayed"
        );
        Log.info("Properties found text is displayed correctly");
        Log.info("TEST COMPLETED SUCCESSFULLY");
        ScreenshotUtil.takeScreenshot(driver, "TC_30_VerifyPropertiesFoundTextIsDisplayed");
    }
}