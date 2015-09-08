package com.activitystream;

import org.json.simple.JSONObject;

public class AddressAspect implements Aspect {
    private String addr;
    private String city;
    private String line2;
    private String countryCode;
    private String zipCode;

    public AddressAspect streetAndNumber(String addr){
        this.addr = addr;
        return this;
    }
    public AddressAspect line2(String line2){
        this.line2 = line2;
        return this;
    }
    public AddressAspect city(String city){
        this.city = city;
        return this;
    }
    public AddressAspect countryCode(String countryCode){
        this.countryCode = countryCode;
        return this;
    }
    public AddressAspect zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    @Override
    public void addToObject(JSONObject jsonObject2) {
        JSONObject addressObj = new JSONObject();
        if (addr != null) addressObj.put("address", addr);
        if (line2 != null) addressObj.put("address2", line2);
        if (city != null) addressObj.put("city", city);
        if (countryCode != null) addressObj.put("country_code", countryCode);
        if (zipCode != null) addressObj.put("zip_code", zipCode);
        jsonObject2.put("address", addressObj);
    }
}
