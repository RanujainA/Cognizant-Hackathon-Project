package org.booking.testcases.aniket;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;

public class TC_04_ValidateSearchButtonFunctionality extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_04_ValidateSearchButtonFunctionality.class);

    @Test
    public void validateSearchButton(){
        HomePage hp = new HomePage(driver);

        log.info("Starting test: Validate Search Button Functionality");

        hp.closePop();
        log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        log.info("Start date selected: " + startDateSelected);

        hp.endDate("7", "May", "2026");
        log.info("End date selected: 7 May 2026");

        hp.enterNumberOfAdults(2);
        log.info("Entered number of adults: 2");

        hp.search();
        log.info("Clicked search button");

        SoftAssert sa = new SoftAssert();
        boolean resultsDisplayed = hp.isSearchResultsDisplayed();
        log.info("Search results displayed: " + resultsDisplayed);

        sa.assertTrue(resultsDisplayed, "Search results are not displayed after clicking search button");
        log.info("Assertion completed: Search results validation");

        sa.assertAll();
        log.info("Soft assertions executed");
    }
}
