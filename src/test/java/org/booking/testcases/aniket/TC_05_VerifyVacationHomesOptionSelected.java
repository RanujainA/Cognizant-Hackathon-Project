package org.booking.testcases.aniket;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;

import java.time.Duration;

public class TC_05_VerifyVacationHomesOptionSelected extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_05_VerifyVacationHomesOptionSelected.class);

    @Test
    public void verifyVacationHomesFilter() {
        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);

        log.info("Starting test: Verify Vacation Homes option selected");

        hp.closePop();
        log.info("Closed popup successfully");

        hp.searchCity("Nairobi");
        log.info("Entered city: Nairobi");

        boolean startDateSelected = hp.startDate("4", "May", "2026");
        log.info("Start date selected: " + startDateSelected);

        hp.endDate("7", "May", "2026");
        log.info("End date selected: 7 May 2026");

        hp.enterNumberOfAdults(2);
        log.info("Entered number of adults: 2");

        hp.search();
        log.info("Clicked search button");

        hsp.clickVacationHomesOption();
        log.info("Clicked Vacation Homes filter");

        SoftAssert sa = new SoftAssert();
        boolean vacationHomesApplied = hsp.isVacationHomesFilterApplied();

        log.info("Vacation Homes filter applied: " + vacationHomesApplied);

        sa.assertTrue(vacationHomesApplied, "Vacation Homes option is not selected");
        log.info("Assertion completed: Vacation Homes filter validation");

        sa.assertAll();
        log.info("Soft assertions executed");
    }
}