package com.activitystream.aspects;

public enum TrafficSourceType {
    DIRECT("direct"),
    CAMPAIGN("campaign"),
    REFERRAL("referral"),
    SEARCH("search"),
    SOCIAL("social");
    private final String type;

    public String getType() {
        return type;
    }

    TrafficSourceType(final String type){
        this.type = type;
    }

}
