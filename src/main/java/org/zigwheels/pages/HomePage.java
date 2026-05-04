package org.zigwheels.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class HomePage {
    WebDriver driver;
    JavascriptExecutor js;
    private static final Logger log = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[@class='e1e158e66b afdff78c97 ce103e449e fe169dc72b']")
    WebElement closePopupBtn;

    @FindBy(xpath="//input[@placeholder='Where are you going?']")
    WebElement searchCity;

    @FindBy(xpath="(//div[@data-testid = 'searchbox-datepicker-calendar']//following::h3)[1]")
    WebElement currentMonthAndYear;

    @FindBy(xpath="//button[@aria-label='Next month']")
    WebElement nextMonthBtn;

    @FindBy(xpath="//button[@data-testid='occupancy-config']")
    WebElement guestSelector;

    @FindBy(xpath="(//span[@class='e32aa465fd'])[1]")
    WebElement checkAdultCount;

    @FindBy(xpath="(//button[@tabindex = '-1'])[2]")
    WebElement adultPlusBtn;

    @FindBy(xpath="(//div[@class='e301a14002']//button)[1]")
    WebElement adultMinusBtn;

    @FindBy(xpath="//span[normalize-space()='Done']")
    WebElement applyBtn;

    @FindBy(xpath="//span[text()='Search']")
    WebElement searchBtn;


    @FindBy(xpath = "//div[contains(text(),'destination')]")
    WebElement destinationTooltip;

    @FindBy(xpath="//div[@data-testid='property-card']")
    List<WebElement> propertyCards;

    @FindBy(xpath="//div[@class='b9b405fa52']")
    WebElement searchCityValidationMsg;

    public void closePop(){
        try{
            WaitUtils.waitForElementToBeClickable(closePopupBtn);
            try{
                closePopupBtn.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click()", closePopupBtn);
                log.warn("Click intercepted, used JavaScript click for closePopupBtn");
            }
        }catch(Exception e2){
            log.error("Failed to close popup: " + e2.getMessage(), e2);
        }
    }
    public void searchCity(String enterCity){
        try{
            WaitUtils.waitForElementToBeVisible(searchCity);
            searchCity.sendKeys(enterCity);
            if(!enterCity.isEmpty()){
                driver.findElement(By.xpath("//div[text()='"+enterCity+"']")).click();
            }
        }catch(Exception e){
            log.error("Failed to enter city: " + e.getMessage(), e);
        }
    }

    public boolean startDate(String startDay, String startMonth, String startYear){
        int dayInt = Integer.parseInt(startDay);
        int yearInt = Integer.parseInt(startYear);
        int monthInt = Month.valueOf(startMonth.toUpperCase()).getValue();
        LocalDate targetDate = LocalDate.of(yearInt, monthInt, dayInt);
        if (targetDate.isBefore(LocalDate.now())) {
            System.out.println("Cannot select a past date: " + targetDate);
            return false;
        }
        while (true) {
            String[] currentMonthYear = currentMonthAndYear.getText().split(" ");
            String currentMonth = currentMonthYear[0];
            String currentYear = currentMonthYear[1];
            if (currentMonth.equalsIgnoreCase(startMonth) && currentYear.equals(startYear)) {
                break;
            }
            try{
                nextMonthBtn.click();
            }catch(ElementClickInterceptedException e){
                js.executeScript("arguments[0].click()", nextMonthBtn);
                log.warn("Click intercepted, used JavaScript click for nextMonthBtn");
            }
        }
        driver.findElement(By.xpath("(//span[text()='"+startDay+"'])[1]")).click();
        return true;
    }

    public void endDate(String endDay, String endMonth, String endYear){
        while (true) {
            String[] currentMonthYear = currentMonthAndYear.getText().split(" ");
            String currentMonth = currentMonthYear[0];
            String currentYear = currentMonthYear[1];
            if (currentMonth.equalsIgnoreCase(endMonth) && currentYear.equals(endYear)) {
                break;
            }
            try{
                nextMonthBtn.click();
            }catch(ElementClickInterceptedException e){
                js.executeScript("arguments[0].click()", nextMonthBtn);
                log.warn("Click intercepted, used JavaScript click for nextMonthBtn");
            }
        }
        driver.findElement(By.xpath("(//span[text()='"+endDay+"'])[1]")).click();
    }

    public boolean enterNumberOfAdults(int targetAdults){
        if(targetAdults <= 0 || targetAdults > 30){
            return false;
        }else{
            try{
                WaitUtils.waitForElementToBeClickable(guestSelector);
                try{
                    guestSelector.click();
                }catch(ElementClickInterceptedException e2){
                    js.executeScript("arguments[0].click()", guestSelector);
                    log.warn("Click intercepted, used JavaScript click for guestSelector");
                }
            }catch(Exception e1){
                log.error("Failed to open guest selector: " + e1.getMessage(), e1);
            }
            int currentAdultsSelected = Integer.parseInt(checkAdultCount.getText());
            while(currentAdultsSelected < targetAdults){
                try{
                    adultPlusBtn.click();
                }catch(ElementClickInterceptedException e){
                    js.executeScript("arguments[0].click()", adultPlusBtn);
                    log.warn("Click intercepted, used JavaScript click for adultPlusBtn");
                }
                currentAdultsSelected = Integer.parseInt(checkAdultCount.getText());
            }
            while(currentAdultsSelected > targetAdults){
                try{
                    adultMinusBtn.click();
                }catch(ElementClickInterceptedException e){
                    js.executeScript("arguments[0].click()", adultMinusBtn);
                    log.warn("Click intercepted, used JavaScript click for adultMinusBtn");
                }
                currentAdultsSelected = Integer.parseInt(checkAdultCount.getText());
            }
            try{
                applyBtn.click();
            }catch(ElementClickInterceptedException e){
                js.executeScript("arguments[0].click()", applyBtn);
                log.warn("Click intercepted, used JavaScript click for applyBtn");
            }
            return true;
        }
    }

    public void search(){
        try{
            WaitUtils.waitForElementToBeClickable(searchBtn);
            try{
                searchBtn.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click()", searchBtn);
                log.warn("Click intercepted, used JavaScript click for searchBtn");
            }
        }catch(Exception e2){
            log.error("Failed to click search button: " + e2.getMessage(), e2);
        }
    }

    public String getDestinationTooltipText() {
        WaitUtils.waitForElementToBeVisible(destinationTooltip);
        return destinationTooltip.getText();
    }

    public boolean isCityEnteredCorrectly(String city) {
        try {
            String actualCity = searchCity.getAttribute("value");
            return actualCity.contains(city);
        }
        catch (Exception e) {
            log.error("Failed to verify entered city: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean isSearchResultsDisplayed() {
        try {
            return !propertyCards.isEmpty();
        } catch (Exception e) {
            log.error("Failed to verify search results: " + e.getMessage(), e);
            return false;
        }
    }

    public boolean showSearchCityValidationMsg(){
        return searchCityValidationMsg.isDisplayed();
    }

}
