package com.fengwenyi.javalib.constant;

/**
 * 日期时间格式常量
 * @author Wenyi Feng
 * @since 2018-10-28
 */
public class DateTimeFormat {

    public static final String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";

    public static final String HH_mm = "HH:mm";

    public static final String [] dateFormats = {
            /* RFC 1123 with 2-digit Year */
            "EEE, dd MMM yy HH:mm:ss z",

            /* RFC 1123 with 4-digit Year */
            "EEE, dd MMM yyyy HH:mm:ss z",

            /* RFC 1123 with no Timezone */
            "EEE, dd MMM yy HH:mm:ss",

            /* Variant of RFC 1123 */
            "EEE, MMM dd yy HH:mm:ss",

            /* RFC 1123 with no Seconds */
            "EEE, dd MMM yy HH:mm z",

            /* Variant of RFC 1123 */
            "EEE dd MMM yyyy HH:mm:ss",

            /* RFC 1123 with no Day */
            "dd MMM yy HH:mm:ss z",

            /* RFC 1123 with no Day or Seconds */
            "dd MMM yy HH:mm z",

            /* ISO 8601 slightly modified */
            "yyyy-MM-dd'T'HH:mm:ssZ",

            /* ISO 8601 slightly modified */
            "yyyy-MM-dd'T'HH:mm:ss'Z'",

            /* ISO 8601 slightly modified */
            "yyyy-MM-dd'T'HH:mm:sszzzz",

            /* ISO 8601 slightly modified */
            "yyyy-MM-dd'T'HH:mm:ss z",

            /* ISO 8601 */
            "yyyy-MM-dd'T'HH:mm:ssz",

            /* ISO 8601 slightly modified */
            "yyyy-MM-dd'T'HH:mm:ss.SSSz",

            /* ISO 8601 slightly modified */
            "yyyy-MM-dd'T'HHmmss.SSSz",

            /* ISO 8601 slightly modified */
            "yyyy-MM-dd'T'HH:mm:ss",

            /* ISO 8601 w/o seconds */
            "yyyy-MM-dd'T'HH:mmZ",

            /* ISO 8601 w/o seconds */
            "yyyy-MM-dd'T'HH:mm'Z'",

            /* RFC 1123 without Day Name */
            "dd MMM yyyy HH:mm:ss z",

            /* RFC 1123 without Day Name and Seconds */
            "dd MMM yyyy HH:mm z",

            /* Simple Date Format */
            "yyyy-MM-dd",

            /* Simple Date Format */
            "MMM dd, yyyy"
    };

}
