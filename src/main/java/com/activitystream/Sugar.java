package com.activitystream;

import com.activitystream.aspects.*;
import com.activitystream.helpers.MapCreator;

import java.util.Map;

public class Sugar {

    public static Entity entity(EntityType type, String id) {
        return new Entity().id(type, id);
    }

    public static Entity entity(String type, String id) {
        return entity(new EntityType(type), id);
    }


    public static EntityRelation rel() {
        return new EntityRelation();
    }

    public static ECommerceAspect eCommerce(ECommerceAspectItem... items) {
        return new ECommerceAspect().items(items);
    }

    public static ECommerceAspectItem item() {
        return new ECommerceAspectItem();
    }

    public static ClassificationAspect classificationAsepct() {
        return new ClassificationAspect();
    }

    public static TimedAspect timedAspect() {
        return new TimedAspect();
    }

    public static Event event(String action) {
        return new Event(action);
    }

    public static ClientIPAddressAspect clientIp(String ip) {return new ClientIPAddressAspect().clientIp(ip);}

    public static ClientDeviceAspect clientDevice(String agent) {return new ClientDeviceAspect().clientDevice(agent);}

    public static LocationAspect location(String latlong){return new LocationAspect(latlong);}

    public static PresentationAspect presentation(){return new PresentationAspect();}

    public static DimensionsAspect dimensions(Map<String, String> dimensions){return new DimensionsAspect(dimensions);}

    public static DimensionsAspect dimensions(MapCreator<String, String> dimensions){return dimensions(dimensions.map());}

    public static MapCreator<String, Object> m(){return new MapCreator<String,Object>();}

    public static MapCreator<String, String> mstr(){return new MapCreator<String, String>();}

}
