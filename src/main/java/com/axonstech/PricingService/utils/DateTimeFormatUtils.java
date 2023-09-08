package com.axonstech.PricingService.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeFormatUtils {
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_WITH_SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_WITH_MILLISECOND_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String SLASH_DATE_FORMAT = "yyyy/MM/dd";
    public static final String LOTUS_DEMAND_FORECAST_DATE_FORMAT = "dd-MMM-yyyy";
    public static final String LOTUS_DEMAND_FORECAST_SLASH_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_BI = "MM/dd/yyyy";
    public static final String DATE_FORMAT_BI_SHORT = "MM/dd/yy";

    public static final String DAY_SHORT_MONTH_NAME = "dd MMM";

    public static final String DAY_FORMAT = "dd";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String MONTH_FORMAT = "yyyy-MM";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_NO_SPACE = "yyyyMMdd";


    private DateTimeFormatUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static String toDateTimeText(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(DATETIME_FORMAT).format(date);
        }
    }

    public static String toDateText(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(DATE_FORMAT).format(date);
        }
    }

    public static Date toDateFromText(String date) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toDateBiText(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(DATE_FORMAT_BI).format(date);
        }
    }

    public static String toSlashDateText(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(SLASH_DATE_FORMAT).format(date);
        }
    }
    public static String toDayShortMonthNameText(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(DAY_SHORT_MONTH_NAME).format(date);
        }
    }


    public static String toYearText(Date date) {
        if (date == null) {
            return "";
        } else {
            return new SimpleDateFormat(YEAR_FORMAT).format(date);
        }
    }

    public static String toLotusFormatLotusRangeDateTime(Date date){
        Calendar inputDateCalendar = new GregorianCalendar();
        inputDateCalendar.setTime(date);
        String startDate = String.valueOf(inputDateCalendar.get(Calendar.DATE));
        inputDateCalendar.add(Calendar.DATE,6);
        String lastDate = new SimpleDateFormat(LOTUS_DEMAND_FORECAST_SLASH_DATE_FORMAT).format(inputDateCalendar.getTime());
        String combineDate = String.format("%s-%s", startDate, lastDate);
        return combineDate;
    }

}
