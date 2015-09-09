package com.activitystream.aspects;

public class ClientIPAddress extends AspectBase {
    public ClientIPAddress() {
        aspectPropertyMap.put("client_ip", new AspectProperty(true));
    }

    public ClientIPAddress clientIp(String ipAddress){
        aspectPropertyMap.get("client_ip").value = ipAddress;
        return this;
    }
}
