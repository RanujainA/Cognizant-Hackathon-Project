package org.booking.testcases;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;

public class TC_14_WonderfulRatingValidation extends BaseTest {

    @Test
    public void verifyWonderfulFilterShowsOnlyWonderfulHotels() {
        HomePage hp = new HomePage(driver);
        hp.closePop();
        hp.searchCity("Nairobi");
        hp.startDate("30", "May", "2026");
        hp.endDate("30", "June", "2026");
        hp.enterNumberOfAdults(4);
        hp.search();
        HotelSearchPage hsp = new HotelSearchPage(driver);
        hsp.clickVacationHomesOption();
        hsp.clickHotelsOption();
        hsp.clickWonderfulOption();
        SoftAssert softAssert = new SoftAssert();
        for (WebElement label : hsp.getReviewLabels()) {
            String text = label.getText();
            softAssert.assertTrue(
                    text.contains("Wonderful") || text.contains("Exceptional"),
                    "FAIL: Other review filter displayed -> " + text
            );

        }
        softAssert.assertAll();

    }
}
