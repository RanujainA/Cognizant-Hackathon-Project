package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) return null;
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotsDir = System.getProperty("user.dir") + File.separator + "screenshots";
            Path dirPath = Paths.get(screenshotsDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            String fileName = testName + "_" + timestamp + ".png";
            Path dest = dirPath.resolve(fileName);
            Files.copy(src.toPath(), dest);
            return dest.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
