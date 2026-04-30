package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.Utility;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        if(Utility.fetchPropertyValue("browser").equals("chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }else if(Utility.fetchPropertyValue("browser").equals("firefox")){
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else if(Utility.fetchPropertyValue("browser").equals("edge")){
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
        driver.get(Utility.fetchPropertyValue("baseUrl").toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(Utility.fetchPropertyValue("implicitWaitSeconds").toString())));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

