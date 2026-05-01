package org.booking.testcases;

import basetest.BaseTest;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;

public class TC_01_ValidateSearchFunctionality extends BaseTest {
    @Test
    public void run(){
        HomePage hp = new HomePage(driver);
        hp.closePop();
        hp.searchCity("Nairobi");
        hp.startDate("30", "May", "2026");
        hp.endDate("30", "June", "2026");
        hp.enterNumberOfAdults(4);
        hp.search();
    }
}
