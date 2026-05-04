package org.booking.testcases.aishwarya;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_11_MaxAdultLimitVerify extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_11_MaxAdultLimitVerify.class);

    @Test
    public void verifyMaxAdultLimit() {

        logger.info("Test started: Verify adult limit is not exceeded");

        HomePage hp = new HomePage(driver);

        logger.info("Closing popup");
        hp.closePop();

        logger.info("Searching city: Nairobi");
        hp.searchCity("Nairobi");

        logger.info("Selecting start date: 30 May 2026");
        hp.startDate("30", "May", "2026");

        logger.info("Selecting end date: 30 June 2026");
        hp.endDate("30", "June", "2026");

        logger.info("Entering number of adults: 4");
        boolean isLimitNotReached = hp.enterNumberOfAdults(4);

        logger.info("Is adult limit NOT reached: {}", isLimitNotReached);

        Assert.assertTrue(
                isLimitNotReached,
                "FAIL: Adult limit was unexpectedly reached for valid input"
        );

        logger.info("Validation successful: Adult limit not reached for valid number");
        logger.info("Test completed successfully");
    }
}