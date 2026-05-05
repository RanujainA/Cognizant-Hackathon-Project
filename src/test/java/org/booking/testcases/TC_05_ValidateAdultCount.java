package org.booking.testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_05_ValidateAdultCount extends BaseTest {

    @Test
    public void validateAdults(){
        HomePage hp = new HomePage(driver);

        Log.info("Starting test: Validate adult count");

        hp.closePop();
        Log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        Log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        Log.info("Start date selected: " + startDateSelected);

        hp.endDate("7", "May", "2026");
        Log.info("End date selected: 7 May 2026");

        boolean adultCountValid = hp.enterNumberOfAdults(0);
        Log.info("Adult count valid: " + adultCountValid);

        Assert.assertFalse(adultCountValid, "Adult count should not be valid when set to 0");
        Log.info("Assertion completed: Adult count validation passed");
        ScreenshotUtil.takeScreenshot(driver, "TC_05_ValidateAdultCount");
    }
}
