package com.activitystream.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateHelpers {

    public static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    public static void validateDateString(String timestamp) {
        SimpleDateFormat formatter = (SimpleDateFormat) dateFormatter.clone();

        try {
            formatter.parse(timestamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
