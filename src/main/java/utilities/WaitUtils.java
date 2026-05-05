package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class WaitUtils {
    public static WebDriverWait wait;

    public WaitUtils(WebDriver driver) throws IOException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(PropertiesUtil.fetchPropertyValue("explicitWaitSeconds").toString())));
    }

    public static void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBePresent(String locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public static WebElement waitForElementToBeVisible(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }
}