package org.zigwheels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PropertyDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    By location = By.xpath("//span[@data-testid='address']");
    By languagesSection = By.xpath("//*[contains(text(),'Languages spoken')]/following::div[1]");

    public PropertyDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getLocation() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(location)).getText();
        } catch (Exception e) {
            return "Location not found";
        }
    }

    public String getLanguages() {
        try {
            return driver.findElement(languagesSection).getText();
        } catch (Exception e) {
            return "Languages not found";
        }
    }
}