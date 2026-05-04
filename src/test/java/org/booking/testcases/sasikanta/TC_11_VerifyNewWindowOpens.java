package org.booking.testcases.sasikanta;

import basetest.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.zigwheels.pages.PropertyDetailsPage;

public class TC_11_VerifyNewWindowOpens extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC_11_VerifyNewWindowOpens.class);

    @Test
    public void verifySeeAvailabilityClickWorks() {

        SoftAssert softAssert = new SoftAssert();
        boolean isPropertyDetailsOpened = true;

        log.info("===== TEST STARTED: Verify See Availability Opens Property Details =====");

        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);

        try {
            log.info("Closing popup if present");
            hp.closePop();

            log.info("Entering city: Nairobi");
            hp.searchCity("Nairobi");

            log.info("Selecting travel dates");
            hp.startDate("30", "May", "2026");
            hp.endDate("5", "June", "2026");

            log.info("Selecting number of adults");
            hp.enterNumberOfAdults(4);

            log.info("Clicking search button");
            hp.search();

            log.info("Applying Hotels filter");
            hsp.clickHotelsOption();

            log.info("Applying Vacation Homes filter");
            hsp.clickVacationHomesOption();

            log.info("Applying Wonderful filter");
            hsp.clickWonderfulOption();

            log.info("Applying Smart Filter: Elevator");
            hsp.enterSmartFilter("Elevator");

            log.info("Clicking 'See Availability' and opening property details");
            pdp.extractHolidayHomeDetails();

            log.info(" See Availability click worked and property details were opened");

        } catch (Exception e) {
            isPropertyDetailsOpened = false;
            log.error(" Failed while clicking See Availability or opening property details", e);
        }

        //  Soft assertion based on EXECUTION SUCCESS
        softAssert.assertTrue(
                isPropertyDetailsOpened,
                "See Availability click failed or property details did not open"
        );

        log.info("===== TEST COMPLETED: Verify See Availability Opens Property Details =====");
        softAssert.assertAll();
    }
}