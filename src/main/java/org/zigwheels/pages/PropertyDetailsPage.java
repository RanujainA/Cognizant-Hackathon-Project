package org.zigwheels.pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtils;

import java.util.List;
import java.util.Set;

public class PropertyDetailsPage {
    WebDriver driver;
    JavascriptExecutor js;
    private static final Logger log =
            LogManager.getLogger(PropertyDetailsPage.class);

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

    @FindBy(xpath="//div[@class='b99b6ef58f cb4b7a25d9 b06461926f']")
    WebElement holidayHomeLocation;

    @FindBy(xpath="(//span[@class='prco-valign-middle-helper'])[1]")
    WebElement holidayHomePrice;

    @FindBy(xpath="//td[@class='totalPrice-container']")
    WebElement containerOfTotalPrice;

    public void extractHolidayHomeDetails(){
        for(int index=0; index<holidayHomes.subList(0, 5).size(); index++){
            WebElement holidayHome = holidayHomes.get(index);
            try{
                WaitUtils.waitForElementToBeVisible(holidayHome);
            }catch(Exception e){
                log.error("Failed because holiday home is not visible : " + e.getMessage(), e);
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
                    log.warn("Click intercepted, used JavaScript click for seeAvailability Button");
                }
            }catch(Exception e2){
                log.error("Failed either because of Holiday Home is not visible or availability button is not clicked : " + e2.getMessage(), e2);
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
                log.info(holidayHomeTitle.getText());
            } catch (Exception e) {
                log.error("Holiday Home Title not visible: " + e.getMessage(), e);
            }
            log.info(holidayHomeLocation.getText());
            js.executeScript("arguments[0].scrollIntoView();", containerOfTotalPrice);
            try{
                WaitUtils.waitForElementToBeVisible(containerOfTotalPrice);
            }catch(Exception e2){
                log.error("Failed because element is not visible: " + e2.getMessage(), e2);
            }
            log.info(holidayHomePrice.getText());
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }
}