package org.booking.testcases.soumik;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import utilities.Log;


public class TC_01_SearchDestinationTooltipTest extends BaseTest {
    @Test
    public void run(){
        HomePage hp = new HomePage(driver);
        hp.closePop();
        hp.search();
        String actualError = hp.getDestinationTooltipText();
        Log.info("Validation message displayed: "+ actualError);
        Assert.assertTrue(
                actualError.toLowerCase().contains("destination"),"Tooltip Validation not displayed"
        );

        Log.info("Destination tooltip validation verified");


    }

}
