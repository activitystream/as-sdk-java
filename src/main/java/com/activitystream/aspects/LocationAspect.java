package com.activitystream.aspects;

public class LocationAspect extends AspectBase{

    public static final String LOCATION_LATLONG = "location.latlong";
    public static final String LOCATION_TYPE = "location.type";

    public LocationAspect(String latlong) {
        aspectPropertyMap.put(LOCATION_LATLONG, new AspectProperty(IsRequired.True));
        aspectPropertyMap.put(LOCATION_TYPE, new AspectProperty(IsRequired.False));
        aspectPropertyMap.get(LOCATION_LATLONG).value = latlong;
    }

    public LocationAspect type(String type) {
        aspectPropertyMap.get(LOCATION_TYPE).value = type;
        return this;
    }

}
