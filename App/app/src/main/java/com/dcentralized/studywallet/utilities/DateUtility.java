package com.dcentralized.studywallet.utilities;

import android.util.Log;

import com.dcentralized.studywallet.activities.LoginActivity;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Used for converting calendars to strings and back
 *
 * @author Tom de Wildt
 */
public class DateUtility {
    private static final String TAG = DateUtility.class.getSimpleName();
    private static final String PATTERN = "EEE MMM dd HH:mm:ss z yyyy";

    /**
     * Converts a calendar to a string
     *
     * @param date in calendar format
     * @return string
     * @author Tom de Wildt
     */
    public static String calendarToString(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
        return sdf.format(date.getTime());
    }

    /**
     * Converts a string back to a calendar
     *
     * @param date in string format
     * @return calendar or null
     * @author Tom de Wildt
     */
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
            Log.e(TAG, "ParseException occurred", e);
            return null;
        }
    }
}
