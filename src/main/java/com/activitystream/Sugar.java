package com.activitystream;

import com.activitystream.aspects.*;
import com.activitystream.helpers.MapCreator;

import java.util.Map;

public class Sugar {

    // Core concepts

    public static Event event(String type) { return new Event(type); }

    public static Event event(EventType type) { return new Event(type); }

    public static Entity entity(EntityType type, String id) { return new Entity(type, id); }

    public static Entity entity(String type, String id) { return entity(new EntityType(type), id); }

    public static Comment comment(String comment) { return new Comment(comment);}

    public static EntityRelation rel() { return new EntityRelation(); }

    public static EntityRole role(EntityRoleType role, Entity entity) { return new EntityRole(role, entity); }

    public static CommentEntityRole role(CommentEntityRoleType role, Entity entity) { return new CommentEntityRole(role, entity); }

    // Aspects

    @Deprecated
    public static ItemsAspect commerce(AspectItem... items) { return new ItemsAspect(items); }

    public static ItemsAspect items(AspectItem... items) { return new ItemsAspect(items); }

    public static AspectItem item() { return new AspectItem(); }

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

    public static TagsAspect tags(String... tags) { return new TagsAspect(tags); }

    public static PageviewAspect pageview(String url) { return new PageviewAspect(url); }

    public static LocaleAspect locale() { return new LocaleAspect(); }

    public static InventoryAspect inventory() { return new InventoryAspect(); }

    public static MessagingAspect messaging() { return new MessagingAspect(); }

    public static AddressAspect address(String streetAndNumber) { return new AddressAspect().streetAndNumber(streetAndNumber); }

    public static AttachmentsAspect attachments(Attachment... attachments) { return new AttachmentsAspect(attachments); }

    public static Attachment attachment(String url) { return new Attachment(url); }

    public static DemographyAspect demography() { return new DemographyAspect(); }

    public static CampaignAspect campaign(String id) { return new CampaignAspect(id); }

    public static AffiliateAspect affiliate(String id) { return new AffiliateAspect(id); }

    public static TrafficSourceAspect trafficSource(TrafficSourceType trafficSourceType) { return new TrafficSourceAspect(trafficSourceType); }


    // helpers to create strongly typed maps

    public static MapCreator<String, Object> m() { return new MapCreator<>(); }

    public static MapCreator<String, String> mstr() { return new MapCreator<>(); }

    public static MapCreator<String, Double> mdbl() { return new MapCreator<>(); }
}
