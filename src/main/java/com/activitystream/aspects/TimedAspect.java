package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class TimedAspect implements Aspect {
    private Date starts;

    public TimedAspect begins(Date starts) {
        this.starts = starts;
        return this;
    }

    private Date ends;

    public TimedAspect ends(Date ends) {
        this.ends = ends;
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
        if (starts != null) timed.put("begins", DateHelpers.isoDateFormatter.format(starts));
        if (ends != null) timed.put("ends", DateHelpers.isoDateFormatter.format(ends));
        timed.put("action", type.toString());
        jsonObject.put("timed", timed);
    }
}
