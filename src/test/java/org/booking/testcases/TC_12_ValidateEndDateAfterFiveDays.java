package org.booking.testcases;
import basetest.BaseTest;
import org.testng.Assert;
import org.zigwheels.pages.HotelSearchPage;
import utilities.DateUtils;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class TC_12_ValidateEndDateAfterFiveDays extends BaseTest {

    @Test
    public void validateEndDateGreaterThanFiveDays() {
        HomePage hp = new HomePage(driver);
        hp.closePop();
        hp.searchCity("Nairobi");
        hp.startDate("1", "May", "2026");
        hp.endDate("5", "May", "2026");
        hp.enterNumberOfAdults(4);
        hp.search();
        HotelSearchPage hsp = new HotelSearchPage(driver);
        hsp.clickVacationHomesOption();
        hsp.clickHotelsOption();
        hsp.clickWonderfulOption();
        hsp.inputElevatorInSmartFilters();

        LocalDate startDate = DateUtils.convertToDate("01", "May", "2026");
        LocalDate endDate   = DateUtils.convertToDate("05", "May", "2026");
        System.out.println(startDate);

        long difference = ChronoUnit.DAYS.between(startDate, endDate);

        Assert.assertTrue(
                difference<=5,
                "End date should be invalid if more than 5 days from start date"
        );


    }
}
