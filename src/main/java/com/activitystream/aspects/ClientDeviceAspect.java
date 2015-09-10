package com.activitystream.aspects;

public class ClientDeviceAspect extends AspectBase {
    public ClientDeviceAspect() {
        aspectPropertyMap.put("client_device", new AspectProperty(true));
    }

    public ClientDeviceAspect clientDevice(String device) {
        aspectPropertyMap.get("client_device").value = device;
        return this;
    }
}
