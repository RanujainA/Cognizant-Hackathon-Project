package org.booking.testcases.soumik;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import utilities.Log;

public class TC_02_VerifySmartFilterInputAcceptanceTest extends BaseTest {
    @Test
    public void run(){
        HomePage hp = new HomePage(driver);
        hp.closePop();
        hp.searchCity("Nairobi");
        hp.startDate("30", "May", "2026");
        hp.endDate("30", "June", "2026");
        hp.enterNumberOfAdults(4);
        hp.search();
        HotelSearchPage hsp = new HotelSearchPage(driver);
        hsp.enterSmartFilter("Elevator");
        String expectedFilter = "Elevator";
        String actualFilter = hsp.getSmartFilterText();
        Assert.assertEquals(actualFilter,expectedFilter,"Smart Filter text area did not accept the input");
        Log.info("Smart Filter text area accepted input correctly");
    }
}
