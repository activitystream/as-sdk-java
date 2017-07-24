package com.activitystream.aspects;

public class ResolvableAspect extends AspectBase {

    public ResolvableAspect(String externalId) {
        aspectPropertyMap.put("resolvable.externalId", new AspectProperty(IsRequired.True, externalId));
        aspectPropertyMap.put("resolvable.batchId", new AspectProperty(IsRequired.False));
    }

    public ResolvableAspect batchId(String batchId) {
        aspectPropertyMap.get("resolvable.batchId").value = batchId;
        return this;
    }

}
