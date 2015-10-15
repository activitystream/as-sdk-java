package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.Map;
import java.util.Set;

public class MetricsAspect implements Aspect {

    private Map<String, Double> dimensions;

    public MetricsAspect(Map<String, Double> dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        jsonObject.put("metrics", dimensions);
    }
}
