package com.fengwenyi.javalib.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Wenyi Feng
 * @since 2018-10-21
 */
public class DateTime {

    /**
     * 1 Day in Millis
     */
    public static final long DAY = 24L * 60L * 60L * 1000L;

    /**
     * 1 Week in Millis
     */
    public static final long WEEK = 7 * DAY;

    /* An array of custom date formats */
    private static final DateFormat[] CUSTOM_DATE_FORMATS;

    /* The Default Timezone to be used */
    private static final TimeZone TIMEZONE = TimeZone.getDefault(); //$NON-NLS-1$

    /**
     * Tries different date formats to parse against the given string
     * representation to retrieve a valid Date object.
     *
     * @param strdate Date as String
     * @return Date The parsed Date
     */
    public static Date parseDate(String strdate) {

        /* Return in case the string date is not set */
        if (strdate == null || strdate.length() == 0) return null;

        Date result = null;
        strdate = strdate.trim();
        if (strdate.length() > 10) {

            /* Open: deal with +4:00 (no zero before hour) */
            if ((strdate.substring(strdate.length() - 5).indexOf("+") == 0 || strdate.substring(strdate.length() - 5).indexOf("-") == 0) && strdate.substring(strdate.length() - 5).indexOf(":") == 2) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                String sign = strdate.substring(strdate.length() - 5, strdate.length() - 4);
                strdate = strdate.substring(0, strdate.length() - 5) + sign + "0" + strdate.substring(strdate.length() - 4); //$NON-NLS-1$
            }

            String dateEnd = strdate.substring(strdate.length() - 6);

            /*
             * try to deal with -05:00 or +02:00 at end of date replace with -0500 or
             * +0200
             */
            if ((dateEnd.indexOf("-") == 0 || dateEnd.indexOf("+") == 0) && dateEnd.indexOf(":") == 3) { //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                if (!"GMT".equals(strdate.substring(strdate.length() - 9, strdate.length() - 6))) { //$NON-NLS-1$
                    String oldDate = strdate;
                    String newEnd = dateEnd.substring(0, 3) + dateEnd.substring(4);
                    strdate = oldDate.substring(0, oldDate.length() - 6) + newEnd;
                }
            }
        }

        /* Try to parse the date */
        int i = 0;
        while (i < CUSTOM_DATE_FORMATS.length) {
            try {

                /*
                 * This Block needs to be synchronized, because the parse-Method in
                 * SimpleDateFormat is not Thread-Safe.
                 */
                synchronized (CUSTOM_DATE_FORMATS[i]) {
                    return CUSTOM_DATE_FORMATS[i].parse(strdate);
                }
            } catch (ParseException e) {
                i++;
            } catch (NumberFormatException e) {
                i++;
            }
        }
        return result;
    }

    /** Initialize the array of common date formats and formatter */
    static {

        /* Create Date Formats */
        final String[] possibleDateFormats = {
                /* RFC 1123 with 2-digit Year */"EEE, dd MMM yy HH:mm:ss z",
                /* RFC 1123 with 4-digit Year */"EEE, dd MMM yyyy HH:mm:ss z",
                /* RFC 1123 with no Timezone */"EEE, dd MMM yy HH:mm:ss",
                /* Variant of RFC 1123 */"EEE, MMM dd yy HH:mm:ss",
                /* RFC 1123 with no Seconds */"EEE, dd MMM yy HH:mm z",
                /* Variant of RFC 1123 */"EEE dd MMM yyyy HH:mm:ss",
                /* RFC 1123 with no Day */"dd MMM yy HH:mm:ss z",
                /* RFC 1123 with no Day or Seconds */"dd MMM yy HH:mm z",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ssZ",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss'Z'",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:sszzzz",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss z",
                /* ISO 8601 */"yyyy-MM-dd'T'HH:mm:ssz",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss.SSSz",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HHmmss.SSSz",
                /* ISO 8601 slightly modified */"yyyy-MM-dd'T'HH:mm:ss",
                /* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mmZ",
                /* ISO 8601 w/o seconds */"yyyy-MM-dd'T'HH:mm'Z'",
                /* RFC 1123 without Day Name */"dd MMM yyyy HH:mm:ss z",
                /* RFC 1123 without Day Name and Seconds */"dd MMM yyyy HH:mm z",
                /* Simple Date Format */"yyyy-MM-dd",
                /* Simple Date Format */"MMM dd, yyyy"};

        /* Create the dateformats */
        CUSTOM_DATE_FORMATS = new SimpleDateFormat[possibleDateFormats.length];

        for (int i = 0; i < possibleDateFormats.length; i++) {
            CUSTOM_DATE_FORMATS[i] = new SimpleDateFormat(possibleDateFormats[i], Locale.getDefault());
            CUSTOM_DATE_FORMATS[i].setTimeZone(TIMEZONE);
        }
    }

    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyy_MM_dd_HH_mm_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String HH_mm = "HH:mm";

    public static final Map<String, SimpleDateFormat> dateFormatMap = new HashMap<>();

    public static String format_yyyy_MM_dd_HH_mm(long timeInMillis) {
        return getDateFormat(yyyy_MM_dd_HH_mm).format(timeInMillis);
    }

    public static String format_HH_mm(long timeInMillis) {
        return getDateFormat(HH_mm).format(timeInMillis);
    }

    public static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat dateFormat = dateFormatMap.get(pattern);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(pattern);
            dateFormatMap.put(pattern, dateFormat);
        }
        return dateFormat;
    }

    public static String format_yyyy_MM_dd_HH_mm(Date date) {
        return getDateFormat(yyyy_MM_dd_HH_mm).format(date);
    }

    public static String format_yyyy_MM_dd_HH_mm_SSS(Date date) {
        return getDateFormat(yyyy_MM_dd_HH_mm_SSS).format(date);
    }


}
