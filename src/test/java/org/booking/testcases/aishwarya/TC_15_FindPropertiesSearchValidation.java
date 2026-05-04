package org.booking.testcases.aishwarya;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_15_FindPropertiesSearchValidation extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_15_FindPropertiesSearchValidation.class);

    @Test
    public void verifyFindPropertiesTriggersSearchResults() {

        logger.info("Test started: Verify Find Properties triggers search results");

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

        logger.info("Clicking search (Find Properties)");
        hp.search();

        HotelSearchPage hsp = new HotelSearchPage(driver);

        logger.info("Applying Vacation Homes filter");
        hsp.clickVacationHomesOption();

        logger.info("Applying Hotels filter");
        hsp.clickHotelsOption();

        logger.info("Applying Wonderful rating filter");
        hsp.clickWonderfulOption();

        logger.info("Entering smart filter: Elevator");
        hsp.enterSmartFilter("Elevator");

        String actualText = hsp.getElevatorLabel().getText();
        logger.info("Captured smart filter label text: {}", actualText);

        Assert.assertEquals(
                actualText,
                "Elevator",
                "Elevator filter failed to apply"
        );

        logger.info("Validation successful: Elevator filter applied correctly");
        logger.info("Test completed");
    }
}