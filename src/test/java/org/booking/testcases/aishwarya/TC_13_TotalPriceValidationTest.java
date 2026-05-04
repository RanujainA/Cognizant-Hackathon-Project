package org.booking.testcases.aishwarya;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.PropertyDetailsPage;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_13_TotalPriceValidationTest extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_13_TotalPriceValidationTest.class);

    @Test
    public void verifyTotalPriceIsExtractedAndParsedCorrectly() {

        logger.info("Test started: Verify total price extraction and parsing");

        HomePage hp = new HomePage(driver);

        logger.info("Closing initial popup");
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

        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        logger.info("Extracting and parsing total prices from property details page");
        List<Double> prices = pdp.getParsedTotalPrices();

        logger.info("Number of prices extracted: {}", prices.size());

        Assert.assertFalse(
                prices.isEmpty(),
                "FAIL: No total prices were extracted from the page"
        );

        for (Double price : prices) {
            logger.info("Validating parsed price value: {}", price);
            softAssert.assertTrue(
                    price > 0,
                    "FAIL: Invalid parsed price found -> " + price
            );
        }

        softAssert.assertAll();

        logger.info("All price validations completed successfully");
        logger.info("Test completed");
    }
}
