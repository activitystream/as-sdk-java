package com.activitystream.aspects;

public class AffiliateAspect extends AspectBase {
    private static final String ID = "affiliate.id";
    private static final String TYPE = "affiliate.type";

    public AffiliateAspect(String id) {
        aspectPropertyMap.put(ID, new AspectProperty(IsRequired.True, id));
        aspectPropertyMap.put(TYPE, new AspectProperty(IsRequired.False,null));
    }

    public AffiliateAspect type(AffiliateType type) {
        aspectPropertyMap.get(TYPE).value = type.getType();
        return this;
    }
}
