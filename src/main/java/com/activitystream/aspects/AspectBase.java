package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AspectBase implements Aspect {
    protected Map<String, AspectProperty> aspectPropertyMap = new HashMap<>();

    @Override
    public void addToObject(JsonObject aspectJson) {
        for (Map.Entry<String, AspectProperty> aspect : aspectPropertyMap.entrySet()) {
            if (aspect.getValue().required && aspect.getValue().value == null) throw new RuntimeException("Property "+aspect.getValue()+" is required ");
            String[] levels = aspect.getKey().split("\\.");
            String aspectPropertyKey = levels[levels.length-1];
            levels = Arrays.copyOf(levels, levels.length - 1);
            JsonObject propertyParent = aspectJson;
            for(String level : levels){
                JsonObject drill = (JsonObject) propertyParent.get(level);
                if (drill == null) {
                    drill = new JsonObject();
                    propertyParent.add(level, drill);
                }
                propertyParent = drill;
            }
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();

            propertyParent.add(aspectPropertyKey, parser.parse(gson.toJson(aspect.getValue().value)));
        }
    }

    protected class AspectProperty {
        public Object value;
        public boolean required;

        public AspectProperty(boolean required) {
            this.required = required;
        }
    }
}
