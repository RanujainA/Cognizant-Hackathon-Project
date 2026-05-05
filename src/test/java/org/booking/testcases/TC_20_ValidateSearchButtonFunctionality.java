package org.booking.testcases;

import basetest.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.booking.pages.HomePage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_20_ValidateSearchButtonFunctionality extends BaseTest {

    @Test
    public void validateSearchButton(){
        HomePage hp = new HomePage(driver);
        Log.info("Starting test: Validate Search Button Functionality");

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

        SoftAssert sa = new SoftAssert();
        boolean resultsDisplayed = hp.isSearchResultsDisplayed();
        Log.info("Search results displayed: " + resultsDisplayed);

        sa.assertTrue(resultsDisplayed, "Search results are not displayed after clicking search button");
        Log.info("Assertion completed: Search results validation");

        sa.assertAll();
        Log.info("Soft assertions executed");
        ScreenshotUtil.takeScreenshot(driver, "TC_20_ValidateSearchButtonFunctionality");
    }
}
