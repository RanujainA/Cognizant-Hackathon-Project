package org.zigwheels.pages;

import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePick {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open calendar
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='date-display-field-start']")
        )).click();

        // Dynamic dates
        LocalDate checkInDate = LocalDate.now().plusDays(3);
        LocalDate checkOutDate = checkInDate.plusDays(4);

        String checkIn = checkInDate.toString();   // yyyy-MM-dd
        String checkOut = checkOutDate.toString();

        // Select dates
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("td[data-date='" + checkIn + "']")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("td[data-date='" + checkOut + "']")
        )).click();
    }
}