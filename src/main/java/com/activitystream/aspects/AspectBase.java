package com.activitystream.aspects;

import com.activitystream.Aspect;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AspectBase implements Aspect {
    protected Map<String, AspectProperty> aspectPropertyMap = new HashMap<>();

    @Override
    public void addToObject(JSONObject aspectJson) {
        for (Map.Entry<String, AspectProperty> aspect : aspectPropertyMap.entrySet()) {
            if (aspect.getValue().required && aspect.getValue().value == null) throw new RuntimeException("Property "+aspect.getValue()+" is required ");
            String[] levels = aspect.getKey().split("\\.");
            String aspectPropertyKey = levels[levels.length-1];
            levels = Arrays.copyOf(levels, levels.length - 1);
            JSONObject propertyParent = aspectJson;
            for(String level : levels){
                JSONObject drill = (JSONObject) propertyParent.get(level);
                if (drill == null) {
                    drill = new JSONObject();
                    propertyParent.put(level, drill);
                }
                propertyParent = drill;
            }
            propertyParent.put(aspectPropertyKey, aspect.getValue().value);
        }
    }

    protected class AspectProperty {
        public String value;
        public boolean required;

        public AspectProperty(boolean required) {
            this.required = required;
        }
    }
}
