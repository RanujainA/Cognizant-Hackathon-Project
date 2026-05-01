package org.zigwheels.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

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

    @FindBy(xpath="//button[@data-testid='searchbox-dates-container']")
    WebElement clickDateElement;

    @FindBy(xpath="(//div[@data-testid = 'searchbox-datepicker-calendar']//following::h3)[1]")
    WebElement currentMonthAndYear;

    @FindBy(xpath="//span[@class='ecb788f3b7 c0b8f1e8f8 d9382a910a']//span")
    WebElement currentDay;

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
            searchCity.sendKeys(Keys.TAB);
        }catch(Exception e){
            log.error("Failed to enter city: " + e.getMessage(), e);
        }
    }

    public void startDate(String startDay, String startMonth, String startYear){
        try{
            WaitUtils.waitForElementToBeClickable(clickDateElement);
            try{
                clickDateElement.click();
            }catch(ElementClickInterceptedException e1){
                js.executeScript("arguments[0].click()", clickDateElement);
                log.warn("Click intercepted, used JavaScript click for clickDateElement");
            }
        }catch(Exception e2){
            log.error("Failed to open date picker: " + e2.getMessage(), e2);
        }
        try{
            WaitUtils.waitForElementToBeVisible(currentMonthAndYear);
            String[] currentMonthYear = currentMonthAndYear.getText().split(" ");
            String currentMonth;
            String currentYear;
            try{
                currentMonth = currentMonthYear[0];
                currentYear = currentMonthYear[1];
            }catch(IndexOutOfBoundsException e){
                log.error("Month/Year parsing failed: " + e.getMessage(), e);
                return;
            }
            String currentDayStr = currentDay.getText();
            int currentDayInt = Integer.parseInt(currentDayStr);
            int startDayInt = Integer.parseInt(startDay);
            while(!currentYear.equals(startYear)){
                try{
                    nextMonthBtn.click();
                }catch(ElementClickInterceptedException e){
                    js.executeScript("arguments[0].click()", nextMonthBtn);
                    log.warn("Click intercepted, used JavaScript click for nextMonthBtn");
                }
                currentMonthYear = currentMonthAndYear.getText().split(" ");
                currentMonth = currentMonthYear[0];
                currentYear = currentMonthYear[1];
            }
            while(!currentMonth.equals(startMonth)){
                try{
                    nextMonthBtn.click();
                }catch(ElementClickInterceptedException e){
                    js.executeScript("arguments[0].click()", nextMonthBtn);
                    log.warn("Click intercepted, used JavaScript click for nextMonthBtn");
                }
                currentMonthYear = currentMonthAndYear.getText().split(" ");
                currentMonth = currentMonthYear[0];
            }
            if(startDayInt >= currentDayInt){
                driver.findElement(By.xpath("(//span[text()='"+startDay+"'])[1]")).click();
            } else {
                log.warn("Expected day is before current day, cannot select.");
            }
        }catch(Exception e){
            log.error("Failed to select start date: " + e.getMessage(), e);
        }
    }

    public void endDate(String endDay, String endMonth, String endYear){
        String[] currentMonthYear = currentMonthAndYear.getText().split(" ");
        String currentMonth = "";
        String currentYear = "";
        try{
            currentMonth = currentMonthYear[0];
            currentYear = currentMonthYear[1];
        }catch(IndexOutOfBoundsException e){
            log.error("Month/Year parsing failed: " + e.getMessage(), e);
            return;
        }
        while(!currentYear.equals(endYear)){
            try{
                nextMonthBtn.click();
            }catch(ElementClickInterceptedException e){
                js.executeScript("arguments[0].click()", nextMonthBtn);
                log.warn("Click intercepted, used JavaScript click for nextMonthBtn");
            }
            currentMonthYear = currentMonthAndYear.getText().split(" ");
            currentMonth = currentMonthYear[0];
            currentYear = currentMonthYear[1];
        }
        while(!currentMonth.equals(endMonth)){
            try{
                nextMonthBtn.click();
            }catch(ElementClickInterceptedException e){
                js.executeScript("arguments[0].click()", nextMonthBtn);
                log.warn("Click intercepted, used JavaScript click for nextMonthBtn");
            }
            currentMonthYear = currentMonthAndYear.getText().split(" ");
            currentMonth = currentMonthYear[0];
        }

        driver.findElement(By.xpath("(//span[text()='"+endDay+"'])[1]")).click();
        clickDateElement.sendKeys(Keys.TAB);
    }

    public void enterNumberOfAdults(int targetAdults){
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
}
