package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.underware.Factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        Map classification = Factories.getMap();
        if (type != null) classification.put("action", type);
        if (categories != null) {
            List jsonArray = new ArrayList();
            Collections.addAll(jsonArray, categories);
            classification.put("categories", jsonArray);
        }
        jsonObject.put("classification", classification);
    }
}
