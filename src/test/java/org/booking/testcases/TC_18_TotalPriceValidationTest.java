package org.booking.testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.booking.pages.HomePage;
import org.booking.pages.PropertyDetailsPage;
import utilities.Log;
import utilities.ScreenshotUtil;

import java.util.List;

public class TC_18_TotalPriceValidationTest extends BaseTest {

    @Test
    public void verifyTotalPriceIsExtractedAndParsedCorrectly() {
        Log.info("Test started: Verify total price extraction and parsing");
        HomePage hp = new HomePage(driver);

        Log.info("Closing initial popup");
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

        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        Log.info("Extracting and parsing total prices from property details page");
        List<Double> prices = pdp.getParsedTotalPrices();

        Log.info("Number of prices extracted: "+ prices.size());

        Assert.assertFalse(
                prices.isEmpty(),
                "FAIL: No total prices were extracted from the page"
        );
        for (Double price : prices) {
            Log.info("Validating parsed price value: "+ price);
            softAssert.assertTrue(
                    price > 0,
                    "FAIL: Invalid parsed price found -> " + price
            );
        }
        softAssert.assertAll();
        Log.info("All price validations completed successfully");
        Log.info("Test completed");
        ScreenshotUtil.takeScreenshot(driver, "TC_18_TotalPriceValidationTest");
    }
}
