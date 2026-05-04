package org.booking.testcases.ranu;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC_01_EmptySearchCityInput extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_01_EmptySearchCityInput.class);

    @Test
    public void searchCity(){
        HomePage hp = new HomePage(driver);

        log.info("Starting test: Verify empty search city input");

        hp.closePop();
        log.info("Closed popup successfully");

        hp.searchCity("");
        log.info("Entered empty city input");

        hp.search();
        log.info("Clicked search button");

        boolean validationMsgDisplayed = hp.showSearchCityValidationMsg();
        log.info("Validation message displayed: " + validationMsgDisplayed);

        Assert.assertTrue(validationMsgDisplayed, "City is entered");
        log.info("Assertion completed: Validation message was shown for empty city input");
    }
}
