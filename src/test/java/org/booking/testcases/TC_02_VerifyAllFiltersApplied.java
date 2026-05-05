package org.booking.testcases;
// Test case: Verify All filters are applied or not
import basetest.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.booking.pages.HomePage;
import org.booking.pages.HotelSearchPage;
import utilities.ExcelUtils;
import utilities.Log;
import utilities.ScreenshotUtil;

import java.io.IOException;

public class TC_02_VerifyAllFiltersApplied extends BaseTest {

    @Test
    public void verifyFiltersApplied() throws IOException {
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);

        Log.info("Starting test: Verify all filters applied");

        hp.closePop();
        Log.info("Closed popup successfully");

        hp.searchCity(ExcelUtils.getCellData(1, 0));
        Log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate(ExcelUtils.getCellData(1, 1), ExcelUtils.getCellData(1, 2), ExcelUtils.getCellData(1, 3));
        Log.info("Start date selected: " + startDateSelected);

        hp.endDate(ExcelUtils.getCellData(1, 4), ExcelUtils.getCellData(1, 5), ExcelUtils.getCellData(1, 6));
        Log.info("End date selected: 7 May 2026");

        hp.enterNumberOfAdults(Integer.parseInt(ExcelUtils.getCellData(1, 7)));
        Log.info("Entered number of adults: 4");

        hp.search();
        Log.info("Clicked search button");

        hsp.clickVacationHomesOption();
        Log.info("Clicked Vacation Homes filter");

        hsp.clickHotelsOption();
        Log.info("Clicked Hotels filter");

        hsp.clickWonderfulOption();
        Log.info("Clicked Wonderful filter");

        hsp.enterSmartFilter(ExcelUtils.getCellData(1, 8));
        Log.info("Entered Elevator in Smart Filters");

        SoftAssert sa = new SoftAssert();
        sa.assertTrue(hsp.isWonderfulFilterApplied(), "Wonderful filter is not applied");
        sa.assertTrue(hsp.isVacationHomesFilterApplied(), "Vacation Homes filter is not applied");
        sa.assertTrue(hsp.isHotelsFilterApplied(), "Hotels filter is not applied");
        sa.assertTrue(hsp.isElevatorFilterApplied(), "Elevator filter is not applied");
        sa.assertAll();

        Log.info("All filter assertions completed");
        ScreenshotUtil.takeScreenshot(driver, "TC_02_VerifyAllFiltersApplied");
    }
}

