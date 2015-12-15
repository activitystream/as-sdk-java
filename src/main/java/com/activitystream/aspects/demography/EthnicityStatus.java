package com.activitystream.aspects.demography;

public enum EthnicityStatus {
    WHITE("White"),
    HISPANIC("Hispanic"),
    AFRICAN_AMERICAN("African American"),
    NATIVE_AMERICAN("Native American"),
    ASIAN("Asian"),
    MULTIRACIAL("Multiracial"),
    OTHER("Other"),
    ;
    private final String status;

    public String getStatus() {
        return status;
    }

    EthnicityStatus(final String status){
        this.status = status;
    }
}
