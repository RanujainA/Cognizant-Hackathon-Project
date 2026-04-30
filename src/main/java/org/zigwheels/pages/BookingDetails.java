package org.zigwheels.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingDetails {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath="//span[normalize-space()='Sort by:']")
    WebElement sortButton;

    @FindBy(xpath="//span[normalize-space()='Top Reviewed']")
    WebElement topReview;
    public BookingDetails(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void sort() throws InterruptedException {
        Thread.sleep(1000);
        sortButton.click();
        Thread.sleep(1000);
        topReview.click();
    }

    public List<WebElement> getTopFiveCards() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@data-testid='property-card'])[position()<=5]")));
    }

    public List<String> getTopFivePropertyDetails() {
        List<WebElement> cards = getTopFiveCards();
        List<String> result = new ArrayList<>();

        for (WebElement card : cards) {

            String name = card.findElement(By.xpath(".//div[@data-testid='title']")).getText();

            String priceText = "";
            try {
                priceText = card.findElement(By.xpath(".//span[@data-testid='price-and-discounted-price']")).getText();
            } catch (Exception ignored) {}

            String ratingText = "";
            try {
                ratingText = card.findElement(By.xpath(".//div[@data-testid='review-score']")).getText();
                ratingText = ratingText.split("\n")[1];
            } catch (Exception ignored) {}

            result.add("Name: " + name + " | Price: " + priceText + " | Rating: " + ratingText);
        }

        return result;
    }
    public void openPropertyByUrl(String url) {
        driver.get(url);
    }

    public List<String> getTopFivePropertyLinks() {

        List<WebElement> cards = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("(//div[@data-testid='property-card'])[position()<=5]")
                )
        );

        List<String> links = new ArrayList<>();

        for (WebElement card : cards) {
            WebElement link = card.findElement(By.xpath(".//a[@data-testid='title-link']"));
            links.add(link.getAttribute("href"));
        }

        return links;
    }


}