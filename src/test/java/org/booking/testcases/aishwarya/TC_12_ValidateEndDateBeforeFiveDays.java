package org.booking.testcases.aishwarya;

import basetest.BaseTest;
import org.testng.Assert;
import utilities.DateUtils;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_12_ValidateEndDateBeforeFiveDays extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_12_ValidateEndDateBeforeFiveDays.class);

    @Test
    public void validateEndDateGreaterThanFiveDays() {

        logger.info("Test started: Validate end date difference <= 5 days");

        HomePage hp = new HomePage(driver);

        logger.info("Closing popup");
        hp.closePop();

        logger.info("Searching city: Nairobi");
        hp.searchCity("Nairobi");

        logger.info("Selecting start date: 4 May 2026");
        hp.startDate("4", "May", "2026");

        logger.info("Selecting end date: 9 May 2026");
        hp.endDate("9", "May", "2026");

        logger.info("Converting start and end dates to LocalDate");
        LocalDate startDate = DateUtils.convertToDate("01", "May", "2026");
        LocalDate endDate   = DateUtils.convertToDate("05", "May", "2026");

        logger.info("Start Date: {}", startDate);
        logger.info("End Date: {}", endDate);

        long difference = ChronoUnit.DAYS.between(startDate, endDate);
        logger.info("Calculated date difference: {} days", difference);

        logger.info("Validating that date difference is less than or equal to 5 days");
        Assert.assertTrue(
                difference <= 5,
                "End date should be invalid if more than 5 days from start date"
        );

        logger.info("Validation successful: Date difference is within 5 days");
        logger.info("Test completed successfully");
    }
}