package org.booking.testcases;

import basetest.BaseTest;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Log;

import java.time.Duration;

public class TC_34_VerifyWebsiteOpensInEdge extends BaseTest {

    @Override
    @BeforeClass
    public void setUp() {

        Log.info("SETUP STARTED : Launching Edge Browser");

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

        Log.info("TEST STARTED : Verify Website Opens in Edge");

        String browserName =
                ((RemoteWebDriver) driver)
                        .getCapabilities()
                        .getBrowserName();

        Log.info("Detected browser name: " + browserName);

        Assert.assertEquals(
                browserName,
                "MicrosoftEdge",
                "Browser is not Edge"
        );
        Log.info("Browser verification passed: Running on Edge");

        String currentUrl = driver.getCurrentUrl();
        Log.info("Current URL loaded: " + currentUrl);

        Assert.assertTrue(
                currentUrl.contains("booking"),
                "Website did not open correctly in Edge"
        );
        Log.info("Website opened successfully in Edge browser");

        Log.info("TEST COMPLETED SUCCESSFULLY");
    }
}