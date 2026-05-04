package org.booking.testcases.ranu;

// Test case: Verify All filters are applied or not

import basetest.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_02_VerifyAllFiltersApplied extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_02_VerifyAllFiltersApplied.class);

    @Test
    public void verifyFiltersApplied(){
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);

        log.info("Starting test: Verify all filters applied");

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

        hsp.clickVacationHomesOption();
        log.info("Clicked Vacation Homes filter");

        hsp.clickHotelsOption();
        log.info("Clicked Hotels filter");

        hsp.clickWonderfulOption();
        log.info("Clicked Wonderful filter");

        hsp.enterSmartFilter("Elevator");
        log.info("Entered Elevator in Smart Filters");

        SoftAssert sa = new SoftAssert();
        sa.assertTrue(hsp.isWonderfulFilterApplied(), "Wonderful filter is not applied");
        sa.assertTrue(hsp.isVacationHomesFilterApplied(), "Vacation Homes filter is not applied");
        sa.assertTrue(hsp.isHotelsFilterApplied(), "Hotels filter is not applied");
        sa.assertTrue(hsp.isElevatorFilterApplied(), "Elevator filter is not applied");
        sa.assertAll();

        log.info("All filter assertions completed");
    }
}
