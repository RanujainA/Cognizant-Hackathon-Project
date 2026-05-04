package org.booking.testcases.ranu;

// Test Case: Invalid Input in Smart Filters

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_03_InvalidInputInSmartFilters extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_03_InvalidInputInSmartFilters.class);

    @Test
    public void enterSmartFilter(){
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);

        log.info("Starting test: Invalid input in Smart Filters");

        hp.closePop();
        log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        log.info("Start date selected: " + startDateSelected);

        hp.endDate("7", "May", "2026");
        log.info("End date selected: 7 May 2026");

        hp.enterNumberOfAdults(4);
        log.info("Entered number of adults: 4");

        hp.search();
        log.info("Clicked search button");

        hsp.enterSmartFilter("XYZ");
        log.info("Entered invalid Smart Filter value: XYZ");

        boolean filterApplied = hsp.isSmartFilterApplied();
        log.info("Smart filter applied status: " + filterApplied);

        Assert.assertFalse(filterApplied, "Smart Filter should not be applied for invalid input");
        log.info("Assertion completed: Invalid Smart Filter was not applied");
    }
}
