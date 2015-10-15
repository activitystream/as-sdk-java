package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.Map;
import java.util.Set;

public class DimensionsAspect implements Aspect {

    private Map<String, String> dimensions;

    public DimensionsAspect(Map<String, String> dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        jsonObject.put("dimensions", dimensions);

    }
}
