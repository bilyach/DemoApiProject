package util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public final static String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    public static String convertDateToString(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return zonedDateTime.format(formatter);
    }

    public static ZonedDateTime convertToDateTime(String date) {
        return ZonedDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
