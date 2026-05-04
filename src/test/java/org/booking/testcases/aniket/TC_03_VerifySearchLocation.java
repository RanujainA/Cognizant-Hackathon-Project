package org.booking.testcases.aniket;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;

public class TC_03_VerifySearchLocation extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_03_VerifySearchLocation.class);

    @Test
    public void verifyValidSearchLocation(){
        HomePage hp = new HomePage(driver);

        log.info("Starting test: Verify valid search location");

        hp.closePop();
        log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        log.info("Entered city: Nairobi");

        SoftAssert sa = new SoftAssert();
        boolean cityEnteredCorrectly = hp.isCityEnteredCorrectly("Nairobi");
        log.info("City entered correctly: " + cityEnteredCorrectly);

        sa.assertTrue(cityEnteredCorrectly, "Search location is not valid");
        log.info("Assertion completed: Search location validation");

        sa.assertAll();
        log.info("Soft assertions executed");
    }
}
