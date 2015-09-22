package com.activitystream;

import com.activitystream.aspects.ClassificationAspect;
import com.activitystream.aspects.ECommerceAspect;
import com.activitystream.aspects.ECommerceAspectItem;
import com.activitystream.aspects.TimedAspect;
import com.activitystream.helpers.MapCreator;

public class Sugar {

    public static EntityRef entityRef(EntityType type, String id) {
        return new EntityRef(type, id);
    }
    public static EntityRef entityRef(String type, String id) {
        return new EntityRef(new EntityType(type), id);
    }

    public static EntityEmbedded entityEmbedded(EntityType type, String id) {
        return new EntityEmbedded().id(type, id);
    }

    public static EntityEmbedded entityEmbedded(String type, String id) {
        return new EntityEmbedded().id(new EntityType(type), id);
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

    public static MapCreator<String, Object> m(){return new MapCreator<String,Object>();}

}
