package org.booking.testcases;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import org.booking.pages.PropertyDetailsPage;
import utilities.Log;
import utilities.ScreenshotUtil;

import java.util.List;

public class TC_29_HotelsSortedByLowPrice extends BaseTest {


    @Test
    public void verifyHotelSortByLowPrice() throws InterruptedException {
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);
        Log.info("Starting test: Verify if Hotels option is selected");

        hp.closePop();
        Log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        Log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("30", "May", "2026");
        Log.info("Start date selected: " + startDateSelected);

        hp.endDate("30", "June", "2026");
        Log.info("End date selected: 30 June 2026");

        hp.enterNumberOfAdults(4);
        Log.info("Entered number of adults: 4");

        hp.search();
        Log.info("Clicked search button");

        hsp.clickVacationHomesOption();
        Log.info("Clicked Vacation Homes filter");

        hsp.clickHotelsOption();
        Log.info("Clicked Hotels filter");

        hsp.enterSmartFilter("Elevator");
        Log.info("Entered Elevator in Smart Filters");
        Thread.sleep(7000);

        List<Integer> rating = hsp.cheapestProperties();
        int limit = Math.min(5, rating.size());
        for (int i = 0; i < limit - 1; i++) {
            Assert.assertTrue(
                    rating.get(i) <= rating.get(i + 1),
                    "Rating order incorrect in first five: " + rating.subList(0, limit)
            );
            Log.info("Assertion completed: Hotels are sorted according to property rating properly");
            ScreenshotUtil.takeScreenshot(driver, "TC_29_HotelsSortedByLowPrice");
        }
    }
}
