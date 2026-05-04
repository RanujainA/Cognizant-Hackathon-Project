package org.booking.testcases.aishwarya;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_14_WonderfulRatingValidation extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_14_WonderfulRatingValidation.class);

    @Test
    public void verifyWonderfulFilterShowsOnlyWonderfulHotels() {

        logger.info("Test started: Verify Wonderful rating filter");

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
        hp.enterNumberOfAdults(4);

        logger.info("Clicking search button");
        hp.search();

        HotelSearchPage hsp = new HotelSearchPage(driver);

        logger.info("Applying Vacation Homes filter");
        hsp.clickVacationHomesOption();

        logger.info("Applying Hotels filter");
        hsp.clickHotelsOption();

        logger.info("Applying Wonderful rating filter");
        hsp.clickWonderfulOption();

        SoftAssert softAssert = new SoftAssert();

        logger.info("Validating review labels shown on search results");

        for (WebElement label : hsp.getReviewLabels()) {
            String text = label.getText();
            logger.info("Review label found: {}", text);

            softAssert.assertTrue(
                    text.contains("Wonderful") || text.contains("Exceptional"),
                    "FAIL: Other review filter displayed -> " + text
            );
        }

        softAssert.assertAll();

        logger.info("All review labels validated successfully");
        logger.info("Test completed");
    }
}