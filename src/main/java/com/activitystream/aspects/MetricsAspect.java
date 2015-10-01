package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.Map;

public class MetricsAspect implements Aspect{

    private Map<String, Double> dimensions;

    public MetricsAspect(Map<String, Double> dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void addToObject(Map jsonObject) {
        if (dimensions != null) {
            jsonObject.put("metrics", dimensions);
        }
    }
}
