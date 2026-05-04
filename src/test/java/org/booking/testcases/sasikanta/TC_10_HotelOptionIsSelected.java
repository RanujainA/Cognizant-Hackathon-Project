package org.booking.testcases.sasikanta;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;

public class TC_10_HotelOptionIsSelected extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(TC_10_HotelOptionIsSelected.class);

    @Test
    public void verifyHotelsOptionIsSelected() {

        log.info("===== TEST STARTED: Verify Hotels Option Selection =====");

        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);

        log.info("Closing popup if present");
        hp.closePop();

        log.info("Entering city: Nairobi");
        hp.searchCity("Nairobi");

        log.info("Selecting travel dates");
        hp.startDate("30", "May", "2026");
        hp.endDate("5", "June", "2026");

        log.info("Selecting number of adults");
        hp.enterNumberOfAdults(4);

        log.info("Clicking Search button");
        hp.search();

        log.info("Attempting to select 'Hotels' filter option");
        hsp.clickHotelsOption();

        //  Hard assertion with log
        Assert.assertTrue(
                hsp.checkHotelsOption.isEnabled(),
                " Hotels option is NOT enabled or could not be selected"
        );

        log.info(" Hotels option is enabled and successfully selected");
        log.info("===== TEST COMPLETED: Verify Hotels Option Selection =====");
    }
}