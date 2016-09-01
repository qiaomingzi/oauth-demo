package com.monkeyk.sos.utils;

import java.text.SimpleDateFormat;

/**
 * 日期处理工具类
 *
 * @author Shengzhao Li
 */
public abstract class DateUtils {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);

    /**
     * Private constructor
     */
    private DateUtils() {
    }

   /* public static LocalDateTime now() {
        return LocalDateTime.now();
    }


    public static String toDateTime(Date date) {
        return toDateTime(date, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String toDateTime(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }


    public static String toDateText(LocalDate date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }
*/
}