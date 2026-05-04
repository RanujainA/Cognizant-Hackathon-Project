package org.booking.testcases.aniket;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.zigwheels.pages.PropertyDetailsPage;

public class TC_06_VerifyHolidayHomesResults extends BaseTest {
    private static final Logger log = LogManager.getLogger(TC_06_VerifyHolidayHomesResults.class);
    @Test
    public void verifyHolidayHomesResults(){
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage bd = new PropertyDetailsPage(driver);

        log.info("Starting test: Verify Holiday Homes results");

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

        hsp.clickVacationHomesOption();
        log.info("Clicked Vacation Homes filter");

        SoftAssert sa = new SoftAssert();
        boolean propertiesDisplayed = bd.arePropertiesDisplayed();
        log.info("Properties displayed: " + propertiesDisplayed);

        sa.assertTrue(propertiesDisplayed, "No properties displayed");

        boolean propertiesContainCity = bd.doPropertiesContainCity("Nairobi");
        log.info("Properties contain city Nairobi: " + propertiesContainCity);

        sa.assertTrue(propertiesContainCity, "Holiday homes in Nairobi are not displayed for given dates");

        sa.assertAll();
        log.info("Soft assertions executed");
    }
}
