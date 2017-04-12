package com.activitystream.aspects;

public class AddressAspect extends AspectBase {

    public AddressAspect() {
        aspectPropertyMap.put("address.address", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.address2", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.city", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.state_code", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.state", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.country_code", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.country", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.zip_code", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.region", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("address.sub_region", new AspectProperty(IsRequired.False));
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

    public AddressAspect country(String country) {
        aspectPropertyMap.get("address.country").value = country;
        return this;
    }

    public AddressAspect zipCode(String zipCode) {
        aspectPropertyMap.get("address.zip_code").value = zipCode;
        return this;
    }

    public AddressAspect stateCode(String stateCode) {
        aspectPropertyMap.get("address.state_code").value = stateCode;
        return this;
    }

    public AddressAspect state(String state) {
        aspectPropertyMap.get("address.state").value = state;
        return this;
    }

    public AddressAspect region(String region) {
        aspectPropertyMap.get("address.region").value = region;
        return this;
    }

    public AddressAspect subRegion(String subRegion) {
        aspectPropertyMap.get("address.sub_region").value = subRegion;
        return this;
    }
}
