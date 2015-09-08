package com.activitystream;

import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddressAspect implements Aspect {

    private Map<String, AspectProperty> aspectPropertyMap = new HashMap<>();

    public AddressAspect() {
        aspectPropertyMap.put("address.address", new AspectProperty(true));
        aspectPropertyMap.put("address.address2", new AspectProperty(false));
        aspectPropertyMap.put("address.city", new AspectProperty(false));
        aspectPropertyMap.put("address.country_code", new AspectProperty(false));
        aspectPropertyMap.put("address.zip_code", new AspectProperty(false));
    }

    public AddressAspect streetAndNumber(String addr){
        aspectPropertyMap.get("address.address").value = addr;
        return this;
    }
    public AddressAspect line2(String line2){
        aspectPropertyMap.get("address.address2").value = line2;
        return this;
    }
    public AddressAspect city(String city){
        aspectPropertyMap.get("address.city").value = city;
        return this;
    }
    public AddressAspect countryCode(String countryCode){
        aspectPropertyMap.get("address.country_code").value = countryCode;
        return this;
    }
    public AddressAspect zipCode(String zipCode) {
        aspectPropertyMap.get("address.zip_code").value = zipCode;
        return this;
    }

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
