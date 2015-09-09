package com.activitystream.aspects;

public class ClientDevice extends AspectBase {
    public ClientDevice() {
        aspectPropertyMap.put("client_device", new AspectProperty(true));
    }

    public ClientDevice clientDevice(String device){
        aspectPropertyMap.get("client_device").value = device;
        return this;
    }
}
