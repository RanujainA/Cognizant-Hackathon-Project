package org.booking.testcases.sasikanta;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.zigwheels.pages.PropertyDetailsPage;

public class TC_13_VerifyHolidayHomesTitleIsDisplayed extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC_13_VerifyHolidayHomesTitleIsDisplayed.class);

    @Test
    public void verifyHolidayHomeTitleDisplayed() {

        log.info("===== TEST STARTED: Verify Holiday Home Title Is Displayed =====");

        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);

        boolean isTitleExtractionSuccessful = true;

        log.info("Closing popup if present");
        hp.closePop();

        log.info("Entering city: Nairobi");
        hp.searchCity("Nairobi");

        log.info("Selecting travel dates");
        hp.startDate("30", "May", "2026");
        hp.endDate("5", "June", "2026");

        log.info("Selecting number of adults");
        hp.enterNumberOfAdults(4);

        log.info("Clicking Search");
        hp.search();

        log.info("Applying filters: Hotels, Vacation Homes, Wonderful");
        hsp.clickHotelsOption();
        hsp.clickVacationHomesOption();
        hsp.clickWonderfulOption();

        log.info("Entering Smart Filter: Elevator");
        hsp.enterSmartFilter("Elevator");

        try {
            log.info("Navigating to property details and extracting titles");
            pdp.extractHolidayHomeDetails();
            log.info(" Holiday Home titles were successfully displayed and logged");
        } catch (Exception e) {
            isTitleExtractionSuccessful = false;
            log.error(" Failed while extracting Holiday Home titles", e);
        }

        //  Added assertion
        Assert.assertTrue(
                isTitleExtractionSuccessful,
                "Holiday Home titles were not displayed or extracted successfully"
        );

        log.info("===== TEST COMPLETED: Verify Holiday Home Title Is Displayed =====");
    }
}