package com.activitystream;

import com.activitystream.aspects.*;
import com.activitystream.helpers.MapCreator;

import java.util.Map;

public class Sugar {

    // Core concepts

    public static Entity entity(EntityType type, String id) { return new Entity().id(type, id); }

    public static Entity entity(String type, String id) { return entity(new EntityType(type), id); }

    public static EntityRelation rel() { return new EntityRelation(); }

    public static EntityRole role(EntityRoleType role, Entity entity) { return new EntityRole(role, entity); }

    public static Event event(String type) { return new Event(type); }

    public static Event event(EventType type) { return new Event(type); }

    // Aspects

    public static ECommerceAspect eCommerce(ECommerceAspectItem... items) { return new ECommerceAspect().items(items); }

    public static ECommerceAspectItem item() { return new ECommerceAspectItem(); }

    public static ClassificationAspect classification() { return new ClassificationAspect(); }

    public static TimedAspect timed() { return new TimedAspect(); }

    public static ClientIPAddressAspect clientIp(String ip) { return new ClientIPAddressAspect().clientIp(ip); }

    public static ClientDeviceAspect clientDevice(String agent) { return new ClientDeviceAspect().clientDevice(agent); }

    public static LocationAspect location(String latlong) { return new LocationAspect(latlong); }

    public static PresentationAspect presentation() { return new PresentationAspect(); }

    public static DimensionsAspect dimensions(Map<String, String> dimensions) { return new DimensionsAspect(dimensions); }

    public static DimensionsAspect dimensions(MapCreator<String, String> dimensions) { return dimensions(dimensions.map()); }

    public static MetricsAspect metrics(Map<String, Double> metrics) { return new MetricsAspect(metrics); }

    public static MetricsAspect metrics(MapCreator<String, Double> metrics) { return metrics(metrics.map()); }

    public static TagsAspect tags(String ... tags) { return new TagsAspect(tags); }

    // helpers to create strongly typed maps

    public static MapCreator<String, Object> m() { return new MapCreator<>(); }

    public static MapCreator<String, String> mstr() { return new MapCreator<>(); }

    public static MapCreator<String, Double> mdbl() { return new MapCreator<>(); }
}
