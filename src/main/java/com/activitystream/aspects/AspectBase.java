package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.underware.Factories;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class AspectBase implements Aspect {
    protected Map<String, AspectProperty> aspectPropertyMap = Factories.getMap();

    @Override
    public void addToObject(Map aspectJson, Set<String> processed) {
        for (Map.Entry<String, AspectProperty> aspect : aspectPropertyMap.entrySet()) {
            if (aspect.getValue().required && aspect.getValue().value == null)
                throw new RuntimeException("Property " + aspect.getValue() + " is required ");
            if (aspect.getValue().value != null){
                String[] levels = aspect.getKey().split("\\.");
                String aspectPropertyKey = levels[levels.length - 1];
                levels = Arrays.copyOf(levels, levels.length - 1);
                Map propertyParent = aspectJson;
                for (String level : levels) {
                    Map drill = (Map) propertyParent.get(level);
                    if (drill == null) {
                        drill = Factories.getMap();
                        propertyParent.put(level, drill);
                    }
                    propertyParent = drill;
                }
                propertyParent.put(aspectPropertyKey, aspect.getValue().value);
            }
        }
    }

    public enum IsRequired {
        True, False
    }

    protected static class AspectProperty {
        public Object value;
        public boolean required;

        public AspectProperty(IsRequired required) {
            this.required = required == IsRequired.True;
        }
    }
}
