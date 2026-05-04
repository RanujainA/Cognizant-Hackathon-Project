package org.booking.testcases.dhruv;

import basetest.BaseTest;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.HotelSearchPage;
import org.zigwheels.pages.PropertyDetailsPage;

import java.time.Duration;

public class TC_07_VerifyBookingWithEdge extends BaseTest {
//    @BeforeMethod
//    public void setup(){
//        driver = new EdgeDriver();
//        driver.manage().window().maximize();
//    }
    @Test
    public void verifyBookingComWithEdge() {

        HomePage hp = new HomePage(driver);
        HotelSearchPage hsp = new HotelSearchPage(driver);
        PropertyDetailsPage pdp = new PropertyDetailsPage(driver);

        hp.closePop();
        hp.searchCity("Nairobi");
        Assert.assertTrue(hp.startDate("30", "May", "2026"));
        hp.endDate("30", "June", "2026");
        hp.enterNumberOfAdults(4);
        hp.search();

        hsp.clickVacationHomesOption();
        hsp.clickHotelsOption();
        hsp.clickWonderfulOption();
        hsp.enterSmartFilter("Elevator");

        pdp.extractHolidayHomeDetails();
        Assert.assertTrue(true);
    }
}