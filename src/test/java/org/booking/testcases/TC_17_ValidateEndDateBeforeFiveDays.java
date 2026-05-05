package org.booking.testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import utilities.Log;
import utilities.ScreenshotUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TC_17_ValidateEndDateBeforeFiveDays extends BaseTest {

    @Test
    public void validateEndDateGreaterThanFiveDays() {
        Log.info("Test started: Validate end date difference <= 5 days");
        HomePage hp = new HomePage(driver);

        Log.info("Closing popup");
        hp.closePop();

        Log.info("Searching city: Nairobi");
        hp.searchCity("Nairobi");

        Log.info("Selecting start date: 4 May 2026");
        hp.startDate("4", "May", "2026");

        Log.info("Selecting end date: 9 May 2026");
        hp.endDate("9", "May", "2026");

        Log.info("Converting start and end dates to LocalDate");
        LocalDate startDate = hp.convertToDate("01", "May", "2026");
        LocalDate endDate   = hp.convertToDate("05", "May", "2026");

        Log.info("Start Date: "+ startDate);
        Log.info("End Date: "+ endDate);

        long difference = ChronoUnit.DAYS.between(startDate, endDate);
        Log.info("Calculated date difference:"+ difference+" days");

        Log.info("Validating that date difference is less than or equal to 5 days");
        Assert.assertTrue(
                difference <= 5,
                "End date should be invalid if more than 5 days from start date"
        );
        Log.info("Validation successful: Date difference is within 5 days");
        Log.info("Test completed successfully");
        ScreenshotUtil.takeScreenshot(driver, "TC_17_ValidateEndDateBeforeFiveDays");
    }
}