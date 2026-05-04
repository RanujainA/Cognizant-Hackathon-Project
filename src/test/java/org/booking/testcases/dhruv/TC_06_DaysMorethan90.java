package org.booking.testcases.dhruv;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;

public class TC_06_DaysMorethan90 extends BaseTest {
    @Test
    public void validateSearchButton(){
        HomePage hp = new HomePage(driver);

        hp.closePop();
        hp.searchCity("Nairobi");
        Assert.assertTrue(hp.startDate("30", "May", "2026"));
        hp.endDate("30", "September", "2026");
        hp.enterNumberOfAdults(2);
        hp.search();

        SoftAssert sa = new SoftAssert();
        sa.assertFalse(driver.getCurrentUrl().contains("searchresults"), "Search results are displayed after 90 days");
        sa.assertAll();
    }
}
