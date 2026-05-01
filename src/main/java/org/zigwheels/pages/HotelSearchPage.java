package org.zigwheels.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

public class HotelSearchPage {

    private static final Logger log =
            LogManager.getLogger(HotelSearchPage.class);

    WebDriver driver;
    JavascriptExecutor js;

    public HotelSearchPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[contains(text(),'Vacation Homes') and @data-testid='filters-group-label-content']")
    WebElement checkVacationOption;

    @FindBy(xpath = "//div[@data-testid='filters-search-widget']")
    WebElement scrollToSmartFilters;

    @FindBy(xpath ="//div[@data-testid='filters-search-widget']//form//div[@class='f6e3a11b0d ae5dbab14d e95943ce9b']//textarea")
    WebElement smartFilterTextArea;

    @FindBy(xpath = "//button[@class='de576f5064 b46cd7aad7 e26a59bb37 c7a901b0e7 f3e59d528f aaf9b6e287 e7f2b1a356 a9d40b8d51']")
    WebElement findPropertiesBtn;

    @FindBy(xpath="//div[contains(text(),'Hotels') and @data-testid='filters-group-label-content']")
    WebElement checkHotelsOption;

    @FindBy(xpath = "//div[contains(text(),'Wonderful') and @data-testid='filters-group-label-content']")
    WebElement checkWonderfulOption;

    public void clickVacationHomesOption() {
        js.executeScript("arguments[0].scrollIntoView();",checkVacationOption);
        try{
            WaitUtils.waitForElementToBeVisible(checkVacationOption);
            WaitUtils.waitForElementToBeClickable(checkVacationOption);
            try{
                checkVacationOption.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click();", checkVacationOption);
                log.warn("Click intercepted, used JavaScript click for checkVacationOption");
            }
        }catch(Exception e2){
            log.error("Failed to click Vacation Homes option: " + e2.getMessage(), e2);
        }
    }

    public void clickHotelsOption(){
        try{
            WaitUtils.waitForElementToBeClickable(checkHotelsOption);
            try{
                checkHotelsOption.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click();", checkHotelsOption);
                log.warn("Click intercepted, used JavaScript click for checkHotelsOption");
            }
        }catch(Exception e2){
            log.error("Failed to click Hotels option: " + e2.getMessage(), e2);
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
                log.warn("Click intercepted, used JavaScript click for checkWonderfulOption");
            }
        }catch(Exception e2){
            log.error("Failed to click Wonderful option: " + e2.getMessage(), e2);
        }
    }

    public void inputElevatorInSmartFilters(){
        js.executeScript("arguments[0].scrollIntoView();",scrollToSmartFilters);
        try{
            WaitUtils.waitForElementToBeVisible(checkWonderfulOption);
            smartFilterTextArea.sendKeys("Elevator");
            WaitUtils.waitForElementToBeClickable(findPropertiesBtn);
            try{
                findPropertiesBtn.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click();", findPropertiesBtn);
                log.warn("Click intercepted, used JavaScript click for findPropertiesBtn");
            }
        }catch(Exception e2){
            log.error("Failed to input Elevator in Smart Filters: " + e2.getMessage(), e2);
        }
    }

}