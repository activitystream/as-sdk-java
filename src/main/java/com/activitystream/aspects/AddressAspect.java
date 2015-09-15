package com.activitystream.aspects;

public class AddressAspect extends AspectBase {

    public AddressAspect() {
        aspectPropertyMap.put("address.address", new AspectProperty(true));
        aspectPropertyMap.put("address.address2", new AspectProperty(false));
        aspectPropertyMap.put("address.city", new AspectProperty(false));
        aspectPropertyMap.put("address.country_code", new AspectProperty(false));
        aspectPropertyMap.put("address.zip_code", new AspectProperty(false));
    }

    public AddressAspect streetAndNumber(String addr) {
        aspectPropertyMap.get("address.address").value = addr;
        return this;
    }

    /**
     * @deprecated this was a bad name, replaced by {@link #secondAddressLine(String)}
     */
    @Deprecated()
    public AddressAspect line2(String line2) {
        aspectPropertyMap.get("address.address2").value = line2;
        return this;
    }

    public AddressAspect secondAddressLine(String secondAddressLine) {
        aspectPropertyMap.get("address.address2").value = secondAddressLine;
        return this;
    }

    public AddressAspect city(String city) {
        aspectPropertyMap.get("address.city").value = city;
        return this;
    }

    public AddressAspect countryCode(String countryCode) {
        aspectPropertyMap.get("address.country_code").value = countryCode;
        return this;
    }

    public AddressAspect zipCode(String zipCode) {
        aspectPropertyMap.get("address.zip_code").value = zipCode;
        return this;
    }

}
