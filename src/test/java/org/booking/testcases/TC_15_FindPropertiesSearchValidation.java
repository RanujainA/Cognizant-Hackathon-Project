package org.booking.testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import utilities.Log;
import utilities.ScreenshotUtil;

public class TC_15_FindPropertiesSearchValidation extends BaseTest {

    @Test
    public void verifyFindPropertiesTriggersSearchResults() {
        Log.info("Test started: Verify Find Properties triggers search results");

        HomePage hp = new HomePage(driver);

        Log.info("Closing popup");
        hp.closePop();

        Log.info("Searching city: Nairobi");
        hp.searchCity("Nairobi");

        Log.info("Selecting start date: 30 May 2026");
        hp.startDate("30", "May", "2026");

        Log.info("Selecting end date: 30 June 2026");
        hp.endDate("30", "June", "2026");

        Log.info("Entering number of adults: 4");
        hp.enterNumberOfAdults(4);

        Log.info("Clicking search (Find Properties)");
        hp.search();
        HotelSearchPage hsp = new HotelSearchPage(driver);

        Log.info("Applying Vacation Homes filter");
        hsp.clickVacationHomesOption();

        Log.info("Applying Hotels filter");
        hsp.clickHotelsOption();

        Log.info("Applying Wonderful rating filter");
        hsp.clickWonderfulOption();

        Log.info("Entering smart filter: Elevator");
        hsp.enterSmartFilter("Elevator");

        String actualText = hsp.getElevatorLabel().getText();
        Log.info("Captured smart filter label text: "+ actualText);
        Assert.assertEquals(
                actualText,
                "Elevator",
                "Elevator filter failed to apply"
        );
        Log.info("Validation successful: Elevator filter applied correctly");
        Log.info("Test completed");
        ScreenshotUtil.takeScreenshot(driver, "TC_15_FindPropertiesSearchValidation");
    }
}