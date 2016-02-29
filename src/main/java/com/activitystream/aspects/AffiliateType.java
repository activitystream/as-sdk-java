package com.activitystream.aspects;

public enum AffiliateType {
    DIRECT("Direct"),
    ORGANIC("Organic"),
    SEARCH("Search"),
    AFFILIATE("Affiliate");
    private final String type;

    public String getType() {
        return type;
    }

    AffiliateType(final String type){
        this.type = type;
    }

}
