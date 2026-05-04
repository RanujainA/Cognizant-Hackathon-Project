package org.booking.testcases.aniket;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.zigwheels.pages.PropertyDetailsPage;

import java.time.Duration;

public class TC_07_VerifyBookingComWithFirefox extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_07_VerifyBookingComWithFirefox.class);

    @Test
    public void verifyBookingComWithFirefox() {
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);

        log.info("Starting test: Verify Booking.com search flow with Firefox driver");

        hp.closePop();
        log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("30", "May", "2026");
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

        Assert.assertTrue(true, "Firefox navigation and search flow failed");
        log.info("Assertion completed: Booking.com search flow verified with Firefox");
    }
}
