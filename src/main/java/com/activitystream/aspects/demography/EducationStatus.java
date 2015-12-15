package com.activitystream.aspects.demography;

public enum EducationStatus {
    NO_SCHOOLING("No Schooling"),
    NURSERY_SCHOOL("Nursery school"),
    PRIMARY_SCHOOL("Primary school"),
    NO_DIPLOMA("No diploma"),
    HIGH_SCHOOL("High school"),
    ASSOCIATE_DEGREE("Associate degree"),
    BACHELOR_DEGREE("Bachelor's degree"),
    MASTER_DEGREE("Master's degree"),
    PROFESSIONAL_DEGREE("Professional degree"),
    DOCTORATE_DEGREE("Doctorate degree"),
    ;
    private final String status;

    public String getStatus() {
        return status;
    }

    EducationStatus(final String status){
        this.status = status;
    }
}
