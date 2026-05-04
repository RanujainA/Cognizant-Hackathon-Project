package org.booking.testcases;

import basetest.BaseTest;
import org.booking.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Log;


public class TC_30_SearchDestinationTooltipTest extends BaseTest {
    @Test
    public void run(){

        Log.info("TEST STARTED : Verify Destination Tooltip Validation");
        Log.info("Initializing Home Page");
        HomePage hp = new HomePage(driver);
        hp.closePop();
        Log.info("Clicking Search button without entering destination");
        hp.search();
        Log.info("Fetching destination tooltip validation message");
        String actualError = hp.getDestinationTooltipText();
        Log.info("Validation message displayed: "+ actualError);
        Assert.assertTrue(
                actualError.toLowerCase().contains("destination"),"Tooltip Validation not displayed"
        );

        Log.info("Destination tooltip validation verified");
        Log.info("TEST COMPLETED SUCCESSFULLY");

    }

}
