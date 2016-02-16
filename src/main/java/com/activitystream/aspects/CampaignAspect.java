package com.activitystream.aspects;

public class CampaignAspect extends AspectBase{
    private static final String ID = "campaign.id";
    private static final String SOURCE = "campaign.source";
    private static final String MEDIUM = "campaign.medium";
    private static final String CONTENT = "campaign.content";
    private static final String TERM = "campaign.term";

    public CampaignAspect(String id) {
        aspectPropertyMap.put(ID, new AspectProperty(IsRequired.True,id));
        aspectPropertyMap.put(SOURCE, new AspectProperty(IsRequired.False,null));
        aspectPropertyMap.put(MEDIUM, new AspectProperty(IsRequired.False, null));
        aspectPropertyMap.put(CONTENT, new AspectProperty(IsRequired.False, null));
        aspectPropertyMap.put(TERM, new AspectProperty(IsRequired.False, null));
    }

    public CampaignAspect source(String source) {
        aspectPropertyMap.get(SOURCE).value = source;
        return this;
    }

    public CampaignAspect medium(String medium) {
        aspectPropertyMap.get(MEDIUM).value = medium;
        return this;
    }

    public CampaignAspect content(String content) {
        aspectPropertyMap.get(CONTENT).value = content;
        return this;
    }

    public CampaignAspect term(String term) {
        aspectPropertyMap.get(TERM).value = term;
        return this;
    }
}
