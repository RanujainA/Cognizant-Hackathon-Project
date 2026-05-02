package org.booking.testcases;

import basetest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;

public class TC_11_MaxAdultLimitVerify extends BaseTest  {
    @Test
    public void verifyMaxAdultLimit() {
        HomePage hp = new HomePage(driver);
        hp.closePop();
        hp.searchCity("Nairobi");
        hp.startDate("30", "May", "2026");
        hp.endDate("30", "June", "2026");
        hp.openGuestSelector();
        hp.increaseAdultsTillMaxLimit();
        Assert.assertTrue(
                hp.isAdultPlusButtonDisabled(),
                "Adults '+' button should be disabled at maximum limit"
        );

    }
}