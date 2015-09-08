package com.activitystream.aspects;

public class ClientIPAddressAspect extends SinglePropertyAspectBase<String> {
    public ClientIPAddressAspect() {
        super("client_ip", true);
    }

}
