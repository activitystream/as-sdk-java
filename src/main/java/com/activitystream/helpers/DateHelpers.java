package com.activitystream.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateHelpers {

    public static void validateDateString(String timestamp) {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        try {
            dateFormatter.parse(timestamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
