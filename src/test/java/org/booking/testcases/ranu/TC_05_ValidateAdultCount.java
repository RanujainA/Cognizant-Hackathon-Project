package org.booking.testcases.ranu;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_05_ValidateAdultCount extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_05_ValidateAdultCount.class);

    @Test
    public void validateAdults(){
        HomePage hp = new HomePage(driver);

        log.info("Starting test: Validate adult count");

        hp.closePop();
        log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        log.info("Start date selected: " + startDateSelected);

        hp.endDate("7", "May", "2026");
        log.info("End date selected: 7 May 2026");

        boolean adultCountValid = hp.enterNumberOfAdults(0);
        log.info("Adult count valid: " + adultCountValid);

        Assert.assertFalse(adultCountValid, "Adult count should not be valid when set to 0");
        log.info("Assertion completed: Adult count validation passed");
    }
}
