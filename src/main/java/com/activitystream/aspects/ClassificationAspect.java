package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.*;

public class ClassificationAspect implements Aspect {

    private String type;

    public ClassificationAspect type(String type) {
        this.type = type;
        return this;
    }

    private String[] categories;

    public ClassificationAspect categories(String... categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject) {
        Map classification = new HashMap();
        if (type != null) classification.put("action", type);
        if (categories != null) {
            List jsonArray = new ArrayList();
            Collections.addAll(jsonArray, categories);
            classification.put("categories", jsonArray);
        }
        jsonObject.put("classification", classification);
    }
}
