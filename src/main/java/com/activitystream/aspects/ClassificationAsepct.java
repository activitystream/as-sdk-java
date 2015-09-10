package com.activitystream.aspects;

import com.activitystream.Aspect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Collections;

public class ClassificationAsepct implements Aspect {

    private String type;

    public ClassificationAsepct type(String type) {
        this.type = type;
        return this;
    }

    private String[] categories;

    public ClassificationAsepct categories(String... categories) {
        this.categories = categories;
        return this;
    }
    @Override
    public void addToObject(JSONObject jsonObject) {
        JSONObject classification = new JSONObject();
        if (type != null) classification.put("type", type);
        if (categories != null) {
            JSONArray jsonArray = new JSONArray();
            Collections.addAll(jsonArray, categories);
            classification.put("categories", jsonArray);
        }
        jsonObject.put("classification", classification);
    }
}
