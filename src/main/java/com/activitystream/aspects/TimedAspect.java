package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.DateHelpers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Collections;
import java.util.Date;

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
    public void addToObject(JSONObject jsonObject) {
        JSONObject timed = new JSONObject();
        if (starts == null && ends == null) {
            throw new RuntimeException("timed aspect needs a start or an end");
        }
        if (starts != null) timed.put("begins", DateHelpers.isoDateFormatter.format(starts));
        if (ends != null) timed.put("ends", DateHelpers.isoDateFormatter.format(ends));
        timed.put("type", type.toString());
        jsonObject.put("timed", timed);
    }
}
