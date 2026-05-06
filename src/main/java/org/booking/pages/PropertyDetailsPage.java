package org.booking.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Log;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PropertyDetailsPage {
    WebDriver driver;
    JavascriptExecutor js;

    public PropertyDetailsPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[@data-testid='property-card']")
    List<WebElement> holidayHomes;

    @FindBy(xpath="//a[@data-testid='availability-cta-btn']")
    List<WebElement> seeAvailability;

    @FindBy(xpath="//div[@data-capla-component-boundary='b-property-web-property-page/PropertyHeaderName']//h2")
    WebElement holidayHomeTitle;

    @FindBy(xpath="(//div[@data-testid='PropertyHeaderAddressDesktop-wrapper']//following-sibling::div//child::div)[1]")
    WebElement holidayHomeLocation;

    @FindBy(xpath="(//span[@class='prco-valign-middle-helper'])[1]")
    WebElement holidayHomePrice;

    @FindBy(xpath="//td[@class='totalPrice-container']")
    WebElement containerOfTotalPrice;

    @FindBy(xpath="(//div[@class='bui-price-display__label '])[1]")
    WebElement tripDurationAndMembers;

    @FindBy(xpath = "//span[@data-testid='price-and-discounted-price']")
    List<WebElement> totalPriceElements;

    public void extractHolidayHomeDetails(){
        try{
            for(int index=0; index<holidayHomes.subList(0, 5).size(); index++){
                WebElement holidayHome = holidayHomes.get(index);
                try{
                    WaitUtils.waitForElementToBeVisible(holidayHome);
                }catch(Exception e){
                    Log.error("Failed because holiday home is not visible : " + e.getMessage(), e);
                }
                WebElement seeAvailabilityBtn = seeAvailability.get(index);
                js.executeScript("arguments[0].scrollIntoView()", holidayHome);
                try{
                    WaitUtils.waitForElementToBeVisible(holidayHome);
                    WaitUtils.waitForElementToBeClickable(seeAvailabilityBtn);
                    try{
                        seeAvailabilityBtn.click();
                    }catch(ElementClickInterceptedException e1){
                        js.executeScript("arguments[0].click();", seeAvailabilityBtn);
                        Log.warn("Click intercepted, used JavaScript click for seeAvailability Button");
                    }
                }catch(Exception e2){
                    Log.error("Failed either because of Holiday Home is not visible or availability button is not clicked : " + e2.getMessage(), e2);
                }
                Set<String> windowHandles = driver.getWindowHandles();
                String mainWindow = driver.getWindowHandle();
                for(String windowHandle : windowHandles){
                    if(!mainWindow.equals(windowHandle)){
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }
                try {
                    WaitUtils.waitForElementToBePresent("//div[@data-capla-component-boundary='b-property-web-property-page/PropertyHeaderName']//h2");
                    WaitUtils.waitForElementToBeVisible(holidayHomeTitle);
                    Log.info("Holiday Home Title: " + holidayHomeTitle.getText());
                } catch (Exception e) {
                    Log.error("Holiday Home Title not visible: " + e.getMessage(), e);
                }
                Log.info("Holiday Home Location: " + holidayHomeLocation.getText());
                js.executeScript("arguments[0].scrollIntoView();", containerOfTotalPrice);
                try{
                    WaitUtils.waitForElementToBeVisible(containerOfTotalPrice);
                }catch(Exception e2){
                    Log.error("Failed because element is not visible: " + e2.getMessage(), e2);
                }
                String totalPriceForAllNightsIncluded = holidayHomePrice.getText();
                Log.info("Holiday Home Total Price (All Nights Included): " + totalPriceForAllNightsIncluded);
                int totalPrice = Integer.parseInt(totalPriceForAllNightsIncluded.replaceAll("[^0-9]", ""));
                String durationText = tripDurationAndMembers.getText().split(",")[0].trim();
                int nights = 0;
                if (durationText.toLowerCase().contains("week")) {
                    String weeksStr = durationText.replaceAll("[^0-9]", "");
                    int weeks = Integer.parseInt(weeksStr);
                    nights = weeks * 7;
                } else if (durationText.toLowerCase().contains("night")) {
                    String nightsStr = durationText.replaceAll("[^0-9]", "");
                    nights = Integer.parseInt(nightsStr);
                } else if (durationText.toLowerCase().contains("day")) {
                    String daysStr = durationText.replaceAll("[^0-9]", "");
                    int days = Integer.parseInt(daysStr);
                    nights = days - 1;
                }
                double perNight = (double) totalPrice / nights;
                Log.info("Duration: " + nights + " nights");
                Log.info("Per Night: ₹" + perNight);
                driver.close();
                driver.switchTo().window(mainWindow);
            }
        }catch(Exception e){
            Log.error("No Holiday Homes Found",e);
        }
    }

    public boolean arePropertiesDisplayed() {
        return holidayHomes != null && !holidayHomes.isEmpty();
    }

    public boolean doPropertiesContainCity(String city) {
        if (holidayHomes == null || holidayHomes.isEmpty()) {
            return false;
        }
        for (WebElement home : holidayHomes) {
            String locationText = home.getText();
            if (locationText.toLowerCase().contains(city.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public List<Double> getParsedTotalPrices() {
        List<Double> parsedPrices = new ArrayList<>();

        for (WebElement priceElement : totalPriceElements) {
            String priceText = priceElement.getText();
            Log.info("Extracted price text: "+ priceText);

            String cleanedPrice = priceText.replaceAll("[^0-9]", "");

            try {
                parsedPrices.add(Double.parseDouble(cleanedPrice));
            } catch (NumberFormatException e) {
                Log.error("Price parsing failed for: " + priceText, e);
            }
        }
        return parsedPrices;
    }

    public boolean verifyLocationDisplayedForFirstFiveHolidayHomes() {
        String mainWindow = driver.getWindowHandle();
        By holidayHomesBy =
                By.xpath("//div[@data-testid='property-card']");
        By seeAvailabilityBy =
                By.xpath("//a[@data-testid='availability-cta-btn']");
        By holidayHomeLocationBy =
                By.xpath("//div[@class='b99b6ef58f cb4b7a25d9 b06461926f']");
        for (int index = 0; index < 5; index++) {
            List<WebElement> homes = driver.findElements(holidayHomesBy);
            List<WebElement> availabilityButtons = driver.findElements(seeAvailabilityBy);
            WebElement holidayHome = homes.get(index);
            WebElement seeAvailabilityBtn = availabilityButtons.get(index);
            WaitUtils.waitForElementToBeVisible(holidayHome);
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    holidayHome
            );
            WaitUtils.waitForElementToBeClickable(seeAvailabilityBtn);
            seeAvailabilityBtn.click();
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(mainWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
            WebElement locationElement =
                    WaitUtils.waitForElementToBeVisible(holidayHomeLocationBy);
            String locationText = locationElement.getText().split("\n")[0].trim();
            Log.info(
                    "Holiday Home Location (index " + (index + 1) + "): " + locationText
            );
            if (locationText.isEmpty()) {
                driver.close();
                driver.switchTo().window(mainWindow);
                return false;
            }
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        return true;
    }

    public boolean verifyPriceIsDisplayedForFirstFiveHomes() {
        String mainWindow = driver.getWindowHandle();
        By holidayHomesBy =
                By.xpath("//div[@data-testid='property-card']");
        By seeAvailabilityBy =
                By.xpath("//a[@data-testid='availability-cta-btn']");
        By totalPriceBy =
                By.xpath("(//span[@class='prco-valign-middle-helper'])[1]");
        for (int index = 0; index < 5; index++) {
            // Re-locate elements to avoid stale issues
            List<WebElement> homes = driver.findElements(holidayHomesBy);
            List<WebElement> availabilityButtons = driver.findElements(seeAvailabilityBy);
            WebElement holidayHome = homes.get(index);
            WebElement seeAvailabilityBtn = availabilityButtons.get(index);
            WaitUtils.waitForElementToBeVisible(holidayHome);
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", holidayHome);
            WaitUtils.waitForElementToBeClickable(seeAvailabilityBtn);
            seeAvailabilityBtn.click();
            // Switch to property details tab
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(mainWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
            WebElement priceElement =
                    WaitUtils.waitForElementToBeVisible(totalPriceBy);
            String priceText = priceElement.getText().trim();
            Log.info("Holiday Home Price (index " + (index + 1) + "): " + priceText);
            if (priceText.isEmpty()) {
                driver.close();
                driver.switchTo().window(mainWindow);
                return false;
            }
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        return true;
    }
}