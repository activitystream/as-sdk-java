package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class TimedAspect implements Aspect {
    private String starts;
    private String ends;
    private TimedStatus type = TimedStatus.PERIOD;

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
        timed.put("begins", starts);
        timed.put("ends", ends);
        timed.put("type", type.toString());
        jsonObject.put("timed", timed);
    }
}
