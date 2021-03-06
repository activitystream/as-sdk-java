package com.activitystream.aspects;

public class ClientIPAddressAspect extends AspectBase {
    public ClientIPAddressAspect() {
        aspectPropertyMap.put("client_ip", new AspectProperty(IsRequired.True));
    }

    public ClientIPAddressAspect clientIp(String ipAddress) {
        aspectPropertyMap.get("client_ip").value = ipAddress;
        return this;
    }
}
