package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.Map;

public class DimensionsAspect implements Aspect{

    private Map<String, String> dimensions;

    public DimensionsAspect(Map<String, String> dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void addToObject(Map jsonObject) {
        if (dimensions != null) {
            jsonObject.put("dimensions", dimensions);
        }
    }
}
