package org.zigwheels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitUtils;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class PropertyDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    By location = (By.xpath("(//*[contains(text(),'Nairobi') and contains(text(),'Kenya')])[3]"));
    @FindBy(xpath = "(//div[@data-testid='facility-group-container']//ul[@class='e9f7361569 b988733741 b049f18dec'])[25]")
    WebElement scrollTo;

    @FindBy(xpath="((//div[contains(.,'Languages spoken')])/span | //div[contains(.,'Languages spoken')])[14]/span")
    List<WebElement> languages;

    public PropertyDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getLocation() {
        try {
            Thread.sleep(1000);
            String loc =  Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(location))).getText();
            return loc.split("\n")[0];
        } catch (Exception e) {
            return "Location not found";
        }
    }

    public String getLanguages() throws InterruptedException {
        String ans = "";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        js.executeScript("arguments[0].scrollIntoView();",scrollTo);
        List<WebElement> languageElements =
                driver.findElements(By.xpath("//div[contains(.,'Languages spoken')]//span"));

        for (WebElement lang : languageElements) {
            ans+=lang.getText();
        }


//            Thread.sleep(1000);
//            for (WebElement language : languages){
//                ans+=language.getAttribute("value");
//            }
        return ans;
    }
}