package org.booking.testcases.ranu;

// Test Case: Verify navigation of driver happening properly or not.

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.zigwheels.pages.PropertyDetailsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_04_ValidateNavigation extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_04_ValidateNavigation.class);

    @Test
    public void verifyNavigationOfDriver(){
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);

        log.info("Starting test: Validate driver navigation");

        hp.closePop();
        log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        log.info("Start date selected: " + startDateSelected);

        hp.endDate("30", "June", "2026");
        log.info("End date selected: 30 June 2026");

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

        pdp.extractHolidayHomeDetails();
        log.info("Extracted holiday home details");

        Assert.assertTrue(hsp.checkPropertiesPageUrl(), "Invalid Navigation");
        log.info("Assertion completed: Navigation to property details page verified");
    }
}
