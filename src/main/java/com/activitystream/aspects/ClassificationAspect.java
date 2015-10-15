package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.underware.Factories;

import java.util.*;

public class ClassificationAspect implements Aspect {

    private String type;
    private String[] categories;

    public ClassificationAspect type(String type) {
        this.type = type;
        return this;
    }

    public ClassificationAspect categories(String... categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        Map classification = Factories.getMap();
        classification.put("action", type);
        if (categories != null) {
            List jsonArray = new ArrayList();
            Collections.addAll(jsonArray, categories);
            classification.put("categories", jsonArray);
        }
        jsonObject.put("classification", classification);
    }
}
