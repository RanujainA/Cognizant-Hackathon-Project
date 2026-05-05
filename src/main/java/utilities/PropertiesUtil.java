package utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static Object fetchPropertyValue(String key) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.get(key);
    }
}


