package com.activitystream.aspects;

public class TrafficSourceAspect extends AspectBase{
    private static final String TYPE = "traffic_source.type";
    private static final String SOURCE = "traffic_source.source";
    private static final String MEDIUM = "traffic_source.medium";
    private static final String CONTENT = "traffic_source.content";
    private static final String TERM = "traffic_source.term";
    private static final String CAMPAIGN = "traffic_source.campaign";
    private static final String REFERRER = "traffic_source.referrer";

    public TrafficSourceAspect(TrafficSourceType trafficSourceType) {
        aspectPropertyMap.put(TYPE, new AspectProperty(IsRequired.True,trafficSourceType.getType()));
        aspectPropertyMap.put(SOURCE, new AspectProperty(IsRequired.False,null));
        aspectPropertyMap.put(MEDIUM, new AspectProperty(IsRequired.False,null));
        aspectPropertyMap.put(CONTENT, new AspectProperty(IsRequired.False,null));
        aspectPropertyMap.put(TERM, new AspectProperty(IsRequired.False,null));
        aspectPropertyMap.put(CAMPAIGN, new AspectProperty(IsRequired.False,null));
        aspectPropertyMap.put(REFERRER, new AspectProperty(IsRequired.False,null));
    }

    public TrafficSourceAspect source(String source) {
        aspectPropertyMap.get(SOURCE).value = source;
        return this;
    }

    public TrafficSourceAspect medium(String medium) {
        aspectPropertyMap.get(MEDIUM).value = medium;
        return this;
    }

    public TrafficSourceAspect content(String content) {
        aspectPropertyMap.get(CONTENT).value = content;
        return this;
    }

    public TrafficSourceAspect term(String term) {
        aspectPropertyMap.get(TERM).value = term;
        return this;
    }

    public TrafficSourceAspect campaign(String campaign) {
        aspectPropertyMap.get(CAMPAIGN).value = campaign;
        return this;
    }

    public TrafficSourceAspect referrer(String referrer) {
        aspectPropertyMap.get(REFERRER).value = referrer;
        return this;
    }
}
