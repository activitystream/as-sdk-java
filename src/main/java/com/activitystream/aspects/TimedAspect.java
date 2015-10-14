package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;

import javax.swing.text.html.HTMLDocument;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class TimedAspect implements Aspect {
    private String starts;

    public TimedAspect begins(Date starts, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);
        this.starts = formatter.format(starts);
        return this;
    }

    public TimedAspect begins(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.starts = timestamp;
        return this;
    }

    private String ends;

    public TimedAspect ends(Date ends, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);
        this.ends = formatter.format(ends);
        return this;
    }

    public TimedAspect ends(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.ends = timestamp;
        return this;
    }

    private TimedStatus type = TimedStatus.VALID;

    public TimedAspect type(TimedStatus type) {
        this.type = type;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        Map timed = Factories.getMap();
        if (starts == null && ends == null) {
            throw new RuntimeException("timed aspect needs a start or an end");
        }
        if (starts != null) timed.put("begins", starts);
        if (ends != null) timed.put("ends", ends);
        timed.put("action", type.toString());
        jsonObject.put("timed", timed);
    }
}
