package org.booking.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Log;
import utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class HotelSearchPage {
    WebDriver driver;
    JavascriptExecutor js;

    public HotelSearchPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@data-testid='price-and-discounted-price']")
    List <WebElement> prices;

    @FindBy(xpath = "//div[@class='ebc566407a']")
    List <WebElement> ratingElements;
    @FindBy(xpath="//div[contains(text(),'Vacation Homes') and @data-testid='filters-group-label-content']")
    WebElement checkVacationOption;

    @FindBy(xpath = "//div[@data-testid='filters-search-widget']")
    WebElement scrollToSmartFilters;

    @FindBy(xpath ="//div[@data-testid='filters-search-widget']//form//div[@class='f6e3a11b0d ae5dbab14d e95943ce9b']//textarea")
    WebElement smartFilterTextArea;

    @FindBy(xpath = "//button[@class='de576f5064 b46cd7aad7 e26a59bb37 c7a901b0e7 f3e59d528f aaf9b6e287 e7f2b1a356 a9d40b8d51']")
    WebElement findPropertiesBtn;

    @FindBy(xpath="//div[contains(text(),'Hotels') and @data-testid='filters-group-label-content']")
    public WebElement checkHotelsOption;

    @FindBy(xpath = "//div[contains(text(),'Wonderful') and @data-testid='filters-group-label-content']")
    WebElement checkWonderfulOption;

    @FindBy(name="review_score=90")
    WebElement checkboxForWonderfulFilter;

    @FindBy(name="ht_id=220")
    WebElement checkboxForVacationHomesFilter;

    @FindBy(name="ht_id=204")
    WebElement checkboxForHotelsFilter;

    @FindBy(xpath="//span[@class='cd46a6a263']//span[text()='Elevator']")
    WebElement elevatorSelection;

    @FindBy(xpath="//div[text()='We couldn’t find any matching filters']")
    WebElement noMatchingFilter;

    @FindBy(xpath = "(//span[text()='Elevator'])[1]")
    WebElement elevator;

    @FindBy(xpath = "//div[@class='f63b14ab7a f546354b44 becbee2f63']")
    List <WebElement> reviewLabels;

    @FindBy(xpath = "//div[@id=':r1l:-note']")
    WebElement emptySmartFilter;

    @FindBy(xpath = "//span[normalize-space()='Sort by:']")
    WebElement sortButton;

    @FindBy(xpath = "//span[normalize-space()='Property rating (high to low)']")
    WebElement topReviewedProperties;

    @FindBy(xpath = "//span[normalize-space()='Price (lowest first)']")
    WebElement cheapestPrices;

    public void clickVacationHomesOption() {
        js.executeScript("arguments[0].scrollIntoView();",checkVacationOption);
        try{
            WaitUtils.waitForElementToBeVisible(checkVacationOption);
            WaitUtils.waitForElementToBeClickable(checkVacationOption);
            try{
                checkVacationOption.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click();", checkVacationOption);
                Log.warn("Click intercepted, used JavaScript click for checkVacationOption");
            }
        }catch(Exception e2){
            Log.error("Failed to click Vacation Homes option: " + e2.getMessage(), e2);
        }
    }

    public void clickHotelsOption(){
        try{
            WaitUtils.waitForElementToBeClickable(checkHotelsOption);
            try{
                checkHotelsOption.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click();", checkHotelsOption);
                Log.warn("Click intercepted, used JavaScript click for checkHotelsOption");
            }
        }catch(Exception e2){
            Log.error("Failed to click Hotels option: " + e2.getMessage(), e2);
        }
    }

    public void clickWonderfulOption(){
        js.executeScript("arguments[0].scrollIntoView();",checkWonderfulOption);
        try{
            WaitUtils.waitForElementToBeVisible(checkWonderfulOption);
            WaitUtils.waitForElementToBeClickable(checkWonderfulOption);
            try{
                checkWonderfulOption.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click();", checkWonderfulOption);
                Log.warn("Click intercepted, used JavaScript click for checkWonderfulOption");
            }
        }catch(Exception e2){
            Log.error("Failed to click Wonderful option: " + e2.getMessage(), e2);
        }
    }

    public void enterSmartFilter(String filterName){
        js.executeScript("arguments[0].scrollIntoView();",scrollToSmartFilters);
        try{
            WaitUtils.waitForElementToBeVisible(checkWonderfulOption);
            smartFilterTextArea.sendKeys(filterName);
            WaitUtils.waitForElementToBeClickable(findPropertiesBtn);
            try{
                findPropertiesBtn.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click();", findPropertiesBtn);
                Log.warn("Click intercepted, used JavaScript click for findPropertiesBtn");
            }
        }catch(Exception e2){
            Log.error("Failed to input Elevator in Smart Filters: " + e2.getMessage(), e2);
        }
    }
    public WebElement getElevatorLabel()
    {
        return   elevator;
    }

    public boolean isWonderfulFilterApplied(){
        return checkboxForWonderfulFilter.isSelected();
    }

    public boolean isVacationHomesFilterApplied(){
        return checkboxForVacationHomesFilter.isSelected();
    }

    public boolean isHotelsFilterApplied(){
        return checkboxForHotelsFilter.isSelected();
    }

    public boolean isElevatorFilterApplied(){
        return elevatorSelection.isDisplayed();
    }

    public boolean isSmartFilterApplied(){
        return !noMatchingFilter.isDisplayed();
    }

    public boolean isNoMatchingFilterMessageDisplayed() {
        try {
            Log.info(emptySmartFilter.getText());
            return emptySmartFilter.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean checkPropertiesPageUrl(){
        return driver.getCurrentUrl().contains("searchresults");
    }

    public List <WebElement> getReviewLabels() {
        return reviewLabels;
    }

    public String getSmartFilterText(){
        String value = smartFilterTextArea.getAttribute("value");
        return value;
    }
    public List<Integer> topReviewedProperties(){
        sortButton.click();
        topReviewedProperties.click();
        List<Integer> rating = new ArrayList<>();

        for (WebElement ratings : ratingElements) {
            rating.add(Integer.parseInt(ratings.getAttribute("aria-label").split(" ")[0]));
        }
        return rating;
    }
    public List<Integer> cheapestProperties(){
        sortButton.click();
        cheapestPrices.click();
        List <Integer> price = new ArrayList<>();
        for (WebElement p : prices) {
            price.add(Integer.parseInt(p.getText().split(" ")[1].replace(",","")));
        }
        return price;
    }
}