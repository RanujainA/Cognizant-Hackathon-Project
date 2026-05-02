package org.zigwheels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitUtils;

import java.time.Duration;

public class PropertyDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    By location = (By.xpath("//*[contains(text(),'Nairobi') and contains(text(),'Kenya')])[3]"));

    @FindBy(xpath="//div[.//span[text()='Languages spoken']]//span[contains(@class,'bui-list__item')]")
    WebElement languagesSection;


    public PropertyDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public String getLocation() {
        try {
            WaitUtils.sleep(1000);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(location)).getAttribute("value");
        } catch (Exception e) {
            return "Location not found";
        }
    }

    public String getLanguages() {
        try {
            WaitUtils.sleep(1000);
            return languagesSection.getAttribute("value");
        } catch (Exception e) {
            return "Languages not found";
        }
    }
}