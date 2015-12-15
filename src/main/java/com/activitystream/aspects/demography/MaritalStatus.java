package com.activitystream.aspects.demography;

public enum MaritalStatus {
    SINGLE("Single"),
    MARRIED("Married"),
    IN_PARTNERSHIP("In-Partnership"),
    WIDOWED("Widowed"),
    DIVORCED("Divorced"),
    SEPARATED("Separated"),
    OTHER("Other"),
    ;
    private final String maritalStatus;

    public String getMaritalStatus() {
        return maritalStatus;
    }

    MaritalStatus(final String maritalStatus){
        this.maritalStatus = maritalStatus;
    }
}
