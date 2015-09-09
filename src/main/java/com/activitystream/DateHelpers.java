package com.activitystream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateHelpers {
    public static DateFormat isoDateFormatter;

    static {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateHelpers.isoDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateHelpers.isoDateFormatter.setTimeZone(tz);
    }

}
