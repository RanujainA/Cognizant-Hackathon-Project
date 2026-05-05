package org.booking.testcases;
import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_14_WonderfulRatingValidation extends BaseTest {

    @Test
    public void verifyWonderfulFilterShowsOnlyWonderfulHotels() {

        Log.info("Test started: Verify Wonderful rating filter");
        HomePage hp = new HomePage(driver);

        Log.info("Closing popup");
        hp.closePop();

        Log.info("Searching city: Nairobi");
        hp.searchCity("Nairobi");

        Log.info("Selecting start date: 30 May 2026");
        hp.startDate("30", "May", "2026");

        Log.info("Selecting end date: 30 June 2026");
        hp.endDate("30", "June", "2026");

        Log.info("Entering number of adults: 4");
        hp.enterNumberOfAdults(4);

        Log.info("Clicking search button");
        hp.search();

        HotelSearchPage hsp = new HotelSearchPage(driver);

        Log.info("Applying Vacation Homes filter");
        hsp.clickVacationHomesOption();

        Log.info("Applying Hotels filter");
        hsp.clickHotelsOption();

        Log.info("Applying Wonderful rating filter");
        hsp.clickWonderfulOption();
        SoftAssert softAssert = new SoftAssert();

        Log.info("Validating review labels shown on search results");
        for (WebElement label : hsp.getReviewLabels()) {
            String text = label.getText();
            Log.info("Review label found: "+ text);
            softAssert.assertTrue(
                    text.contains("Wonderful") || text.contains("Exceptional"),
                    "FAIL: Other review filter displayed -> " + text
            );
        }
        softAssert.assertAll();
        Log.info("All review labels validated successfully");
        Log.info("Test completed");
        ScreenshotUtil.takeScreenshot(driver, "TC_14_WonderfulRatingValidation");
    }
}