package org.booking.testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_08_InvalidDateTest extends BaseTest {

    @Test
    public void verifyInvalidStartDate_MessageOnly() {

        HomePage hp = new HomePage(driver);

        Log.info("Starting TC_08_InvalidDateTest");

        hp.closePop();
        Log.info("Popup closed successfully");

        hp.searchCity("Nairobi");
        Log.info("City entered: Nairobi");

        boolean isDateSelected = hp.startDate("3", "April", "2026");
        Log.info("Attempted to select past date: 3 April 2026");
        //  Hard assertion
        Assert.assertFalse(
                isDateSelected,
                " Invalid date provided: Start date 3 April 2026 " +
                        "is before the current date and should not be selectable."
        );
        Log.info(" Past date selection correctly blocked");
        ScreenshotUtil.takeScreenshot(driver, "TC_08_InvalidDateTest");
    }
}