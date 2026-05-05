package org.booking.testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_01_EmptySearchCityInput extends BaseTest {
    @Test
    public void searchCity(){
        HomePage hp = new HomePage(driver);

        Log.info("Starting test: Verify empty search city input");

        hp.closePop();
        Log.info("Closed popup successfully");

        hp.searchCity("");
        Log.info("Entered empty city input");

        hp.search();
        Log.info("Clicked search button");

        boolean validationMsgDisplayed = hp.showSearchCityValidationMsg();
        Log.info("Validation message displayed: " + validationMsgDisplayed);

        Assert.assertTrue(validationMsgDisplayed, "City is entered");
        Log.info("Assertion completed: Validation message was shown for empty city input");

        ScreenshotUtil.takeScreenshot(driver, "TC_01_EmptySearchCityInput");
    }
}
