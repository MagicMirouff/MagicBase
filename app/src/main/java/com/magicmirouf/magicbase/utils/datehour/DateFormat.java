package com.magicmirouf.magicbase.utils.datehour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sylva on 16/08/2016.
 */
public class DateFormat {

    private static final String FORMAT_BASE = "yyy-MM-dd HH:mm:ss";
    private static final String FORMAT_UNIVERSEL = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /**
     * @param date to convert
     * @param format
     * base format : yyyy-MM-dd HH:mm:ss
     * @return String format date
     */
    public static String dateToString(Date date, String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    /**
     * @param date_string to convert
     * @param format
     * base format : yyyy-MM-dd HH:mm:ss.SSSZ
     * @return Date from date_string
     */
    public static Date StringToDate(String date_string, String format){
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat(format, Locale.getDefault());
        try {
            return simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date toDate(String date_string){
        return StringToDate(date_string,FORMAT_BASE);
    }

    public static Date toDateStandard(String date_string){
        return StringToDate(date_string,FORMAT_UNIVERSEL);
    }

    public static String toString(Date date){
        return dateToString(date,FORMAT_BASE);
    }

    public static String toStandard(Date date){
        return dateToString(date,FORMAT_UNIVERSEL);
    }

    public static String toDisplay(String date_string, String format){
        Date date = toDate(date_string);
        return dateToString(date,format);
    }


    /**
     * @param date
     * @return 'le' dd MMMM yyyy 'à' HH:mm
     */
    public static String toDisplay(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'le' dd MMMM yyyy 'à' HH:mm", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    /**
     * @param date
     * @return dd MMMM yyyy
     */
    public static String toDisplayDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }


    /**
     * @param date
     * @return dd'/'MM'/'yyyy
     */
    public static String toDisplayDateNumber(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd'/'MM'/'yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String toDisplayDateNumber(Date date,String separator){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd'" + separator+ "'MM'"+separator+"'yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String toDisplayCCV(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM'/'yy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }


    /**
     * @param date
     * @return "HH:mm"
     */
    public static String toDisplayHour(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return simpleDateFormat.format(date);
    }


}
