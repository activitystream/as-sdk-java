package com.activitystream.aspects;

public class SinglePropertyAspectBase<T> extends AspectBase {
    private String propName;

    public SinglePropertyAspectBase(String propName, Boolean isRequired) {
        this.propName = propName;
        aspectPropertyMap.put(propName, new AspectProperty(isRequired));
    }

    public SinglePropertyAspectBase value(T newValue){
        aspectPropertyMap.get(propName).value = newValue;
        return this;
    }
}
