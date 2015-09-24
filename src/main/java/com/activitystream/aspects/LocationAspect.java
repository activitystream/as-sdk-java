package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.HashMap;
import java.util.Map;

public class LocationAspect implements Aspect{

    private String type;
    private String latlong;

    public LocationAspect(String latlong) {
        this.latlong = latlong;
    }

    public LocationAspect type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject) {
        Map result = new HashMap();
        if (latlong != null) result.put("latlong", latlong);
        if (type != null) result.put("type", type);
        jsonObject.put("location", result);
    }
}
