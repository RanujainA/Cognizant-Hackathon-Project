package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate convertToDate(String day, String month, String year) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        return LocalDate.parse(day + "-" + month + "-" + year, formatter);
    }
}
