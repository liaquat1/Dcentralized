package com.dcentralized.studywallet.utilities;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtility {
    private static final String PATTERN = "EEE MMM dd HH:mm:ss z yyyy";

    public static String calendarToString(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
        return sdf.format(date.getTime());
    }

    public static Calendar stringToCalendar(String date) {
        try {
            if (date != null && !StringUtils.isBlank(date)) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
                calendar.setTime(sdf.parse(date));
                return calendar;
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
