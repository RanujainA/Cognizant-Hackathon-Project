package org.booking.testcases.sasikanta;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;

public class TC_08_InvalidDateTest extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC_08_InvalidDateTest.class);

    @Test
    public void verifyInvalidStartDate_MessageOnly() {

        HomePage hp = new HomePage(driver);

        log.info("Starting TC_08_InvalidDateTest");

        hp.closePop();
        log.info("Popup closed successfully");

        hp.searchCity("Nairobi");
        log.info("City entered: Nairobi");

        boolean isDateSelected = hp.startDate("3", "April", "2026");

        log.info("Attempted to select past date: 3 April 2026");

        //  Hard assertion
        Assert.assertFalse(
                isDateSelected,
                " Invalid date provided: Start date 3 April 2026 " +
                        "is before the current date and should not be selectable."
        );

        log.info(" Past date selection correctly blocked");
    }
}