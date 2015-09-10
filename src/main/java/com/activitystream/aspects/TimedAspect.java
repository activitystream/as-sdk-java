package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.DateHelpers;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
    public void addToObject(JsonObject jsonObject) {
        JsonObject timed = new JsonObject();
        if (starts == null && ends == null) {
            throw new RuntimeException("timed aspect needs a start or an end");
        }
        if (starts != null) timed.add("begins", new JsonPrimitive(DateHelpers.isoDateFormatter.format(starts)));
        if (ends != null) timed.add("ends", new JsonPrimitive(DateHelpers.isoDateFormatter.format(ends)));
        timed.add("action", new JsonPrimitive(type.toString()));
        jsonObject.add("timed", timed);
    }
}
