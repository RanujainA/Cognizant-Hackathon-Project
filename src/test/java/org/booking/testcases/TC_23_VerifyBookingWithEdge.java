package org.booking.testcases;
import basetest.BaseTest;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import utilities.Log;
import java.time.Duration;
import utilities.ScreenshotUtil;

public class TC_23_VerifyBookingWithEdge extends BaseTest {

    @BeforeClass
    public void setUp(){
        driver = new EdgeDriver();
        Log.info("Edge browser launched successfully");

        driver.manage().window().maximize();
        Log.info("Browser window maximized");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Log.info("Implicit wait set to 10 seconds");

        driver.get("https://www.booking.com");
        Log.info("Navigated to URL: https://www.booking.com");

        Log.info("SETUP COMPLETED SUCCESSFULLY");
    }
    @Test
    public void verifyWebsiteOpenedInEdgeBrowser() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        System.out.println(browserName);
        Assert.assertEquals(browserName,"MicrosoftEdge","Microsoft Edge is not working with the website");
        String currentUrl = driver.getCurrentUrl();
        Log.info("Current URL loaded: " + currentUrl);
        Assert.assertTrue(
                currentUrl.contains("booking"),
                "Website did not open correctly in Edge"
        );
        ScreenshotUtil.takeScreenshot(driver, "TC_23_VerifyBookingWithEdge");
        Log.info("Website opened successfully in Edge browser");
        Log.info("TEST COMPLETED SUCCESSFULLY");
    }
}