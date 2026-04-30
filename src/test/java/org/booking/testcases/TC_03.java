package org.booking.testcases;

import basetest.BaseTest;
import org.testng.annotations.Test;
import org.zigwheels.pages.HomePage;
import org.zigwheels.pages.BookingDetails;
import org.zigwheels.pages.PropertyDetailsPage;


import java.util.List;

public class TC_03 extends BaseTest {

    @Test
    public void getTopFiveProperties() throws InterruptedException {

        HomePage hp = new HomePage(driver);
        hp.closePop();
        Thread.sleep(1000);
        hp.searchCity("Nairobi");
        Thread.sleep(1000);
        hp.startDate("30", "May", "2026");
        Thread.sleep(1000);
        hp.endDate("30", "June", "2026");
        hp.enterNumberOfAdults(4);
        hp.search();
        PropertyDetailsPage prop = new PropertyDetailsPage(driver);

        BookingDetails resultsPage = new BookingDetails(driver);
        String location = prop.getLocation();
        String languages = prop.getLanguages();

        List<String> properties = resultsPage.getTopFivePropertyDetails();
        List<String> links = resultsPage.getTopFivePropertyLinks();

        for (String property : properties) {
            System.out.println(property);
            System.out.println("Location: " + location);
            System.out.println("Languages: " + languages);
        }
    }
}