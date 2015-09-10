package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Collections;

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
    public void addToObject(JsonObject jsonObject) {
        JsonObject classification = new JsonObject();
        if (type != null) classification.add("action", new JsonPrimitive(type));
        if (categories != null) {
            JsonArray jsonArray = new JsonArray();
            for(String item : categories) jsonArray.add(new JsonPrimitive(item));
            classification.add("categories", jsonArray);
        }
        jsonObject.add("classification", classification);
    }
}
