package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.underware.Factories;

import java.util.*;

public class ClassificationAspect implements Aspect {

    private String type;
    private List<String> categories = new ArrayList<>();
    private String make;
    private String model;
    private String size;
    private Integer year;
    private String variant;

    public ClassificationAspect type(String type) {
        this.type = type;
        return this;
    }

    public ClassificationAspect categories(String... categories) {
        this.categories.addAll(Arrays.asList(categories));
        return this;
    }

    public ClassificationAspect make(String make) {
        this.make = make;
        return this;
    }

    public ClassificationAspect model(String model) {
        this.model = model;
        return this;
    }

    public ClassificationAspect size(String size) {
        this.size = size;
        return this;
    }

    public ClassificationAspect year(Integer year) {
        this.year = year;
        return this;
    }

    public ClassificationAspect variant(String variant) {
        this.variant = variant;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        Map classification = Factories.getMap();
        classification.put("type", type);
        classification.put("categories", categories);
        classification.put("make", make);
        classification.put("model", model);
        classification.put("variant", variant);
        classification.put("size", size);
        classification.put("year", year);
        jsonObject.put("classification", classification);
    }
}
