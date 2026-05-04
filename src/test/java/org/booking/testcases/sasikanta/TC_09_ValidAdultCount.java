package org.booking.testcases.sasikanta;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;

public class TC_09_ValidAdultCount extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC_09_ValidAdultCount.class);

    @Test
    public void verifyValidAdultCount_Positive() {

        log.info("Starting TC_09_ValidAdultCount");

        SoftAssert softAssert = new SoftAssert();
        HomePage hp = new HomePage(driver);

        hp.closePop();
        log.info("Popup closed successfully");

        hp.searchCity("Nairobi");
        log.info("City entered: Nairobi");

        hp.startDate("30", "May", "2026");
        log.info("Start date selected: 30 May 2026");

        hp.endDate("5", "June", "2026");
        log.info("End date selected: 5 June 2026");

        int adults = 4;
        log.info("Attempting to select adult count: {}", adults);

        softAssert.assertTrue(adults >= 1 && adults <= 30, " Adult count must be between 1 and 30");

        hp.enterNumberOfAdults(adults);
        log.info("Adult count selected successfully: {}", adults);

        softAssert.assertAll();
        log.info(" TC_09_ValidAdultCount completed successfully");
    }
}