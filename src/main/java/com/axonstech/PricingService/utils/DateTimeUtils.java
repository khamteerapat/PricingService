package com.axonstech.PricingService.utils;


import io.micrometer.common.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    private DateTimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PROJECT_RESPONSE_DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String PROJECT_RESPONSE_DATE_FORMAT = "dd/MM/yyyy";
    public static Date parseDate(String dateText) {
        Date date = null;

        try {
            date = parseOrThrowDate(dateText);
        } catch (ParseException parseException) {
            ExceptionUtils.log(parseException);
        }
        return date;
    }

    public static Date parseOrThrowDate(String dateText) throws ParseException {
        return new SimpleDateFormat(DateTimeFormatUtils.DATE_FORMAT).parse(dateText);
    }

    public static Date parseDateWithSecondFormat(String dateText) throws ParseException {
        return new SimpleDateFormat(DateTimeFormatUtils.DATETIME_WITH_SECOND_FORMAT).parse(dateText);
    }

    public static Date parseDateNoSpace(String dateText) throws ParseException {
        return new SimpleDateFormat(DateTimeFormatUtils.DATE_NO_SPACE).parse(dateText);
    }


    public static Date parseYear(String yearText) {
        Date date = null;

        try {
            date = parseOrThrowDate(yearText);
        } catch (ParseException parseException) {
            ExceptionUtils.log(parseException);
        }
        return date;
    }

    public static Date parseOrThrowYear(String yearText) throws ParseException {
        return new SimpleDateFormat(DateTimeFormatUtils.YEAR_FORMAT).parse(yearText);
    }


    public static Date parseSlashDate(String dateText) {
        Date date = null;

        try {
            date = new SimpleDateFormat(DateTimeFormatUtils.SLASH_DATE_FORMAT).parse(dateText);
        } catch (ParseException parseException) {
            ExceptionUtils.log(parseException);
        }
        return date;
    }

    public static Date parseBiDate(String dateText) {
        Date date = null;

        try {
            date = new SimpleDateFormat(DateTimeFormatUtils.DATE_FORMAT_BI_SHORT).parse(dateText);
        } catch (ParseException parseException) {
            ExceptionUtils.log(parseException);
        }
        return date;
    }


    public static Date parseOrThrowLotusForecastDate(String lotusForecastDateText) throws ParseException {
        return new SimpleDateFormat(DateTimeFormatUtils.LOTUS_DEMAND_FORECAST_DATE_FORMAT).parse(lotusForecastDateText);
    }


    public static String nowDateTimeWithSecondText() {
        return new SimpleDateFormat(DateTimeFormatUtils.DATETIME_WITH_SECOND_FORMAT).format(Calendar.getInstance().getTime());
    }

    public static String nowDateTimeWithMillisecondText() {
        return new SimpleDateFormat(DateTimeFormatUtils.DATETIME_WITH_MILLISECOND_FORMAT).format(Calendar.getInstance().getTime());
    }

    public static Date stringToDate(String dateStr,String format) throws ParseException {
        if(StringUtils.isEmpty(dateStr)) return null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateStr);
    }
    public static String dateToString(Date date,String format){
        if(date == null) return null;
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static Date thisYearDate(int dayOfMonth,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        calendar.set(Calendar.MONTH,month - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date inputDate(int dayOfMonth,int month,int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        calendar.set(Calendar.MONTH,month - 1);
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Integer getWeekOfYear(Date date){
        if(date == null) return null;
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());
        return currentCalendar.get(Calendar.WEEK_OF_YEAR);
    }
    public static Integer getCurrentYear(){
        Calendar currentCalendar = Calendar.getInstance();
        return currentCalendar.get(Calendar.YEAR);
    }

    public static String getYearStrOfDate(Date date){
        if(date == null) return null;
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        return String.valueOf(time.get(Calendar.YEAR));
    }
    public static Integer getMonthOfDate(Date date){
        if(date == null) return null;
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        return time.get(Calendar.MONTH) + 1;
    }
}
