package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.underware.Factories;

import java.util.*;

public class ClassificationAspect implements Aspect {

    private String type;
    private List<String> categories = new ArrayList<>();

    public ClassificationAspect type(String type) {
        this.type = type;
        return this;
    }

    public ClassificationAspect categories(String... categories) {
        this.categories.addAll(Arrays.asList(categories));
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        Map classification = Factories.getMap();
        classification.put("action", type);
        classification.put("categories", categories);
        jsonObject.put("classification", classification);
    }
}
