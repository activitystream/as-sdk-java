package com.activitystream.aspects.demography;

public enum HousingStatus {
    PARTIALLY_OWNED("Partially Owned"),
    FULLY_OWNED("Fully Owned"),
    RENT("Rent"),
    RENT_FREE("Rent-Free"),
    OTHER("Other"),
    ;
    private final String status;

    public String getStatus() {
        return status;
    }

    HousingStatus(final String status){
        this.status = status;
    }
}
