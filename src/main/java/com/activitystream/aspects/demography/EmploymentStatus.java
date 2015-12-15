package com.activitystream.aspects.demography;

public enum EmploymentStatus {
    EMPLOYED("Employed"),
    SELF_EMPLOYED("Self-employed"),
    OUT_OF_WORK_AND_LOOKING("Out of work and looking"),
    OUT_OF_WORK_NOT_LOOKING("Out of work not looking"),
    HOMEMAKER("homemaker"),
    STUDENT("student"),
    MILITARY("Military"),
    RETIRED("Retired"),
    UNABLE_TO_WORK("Unable to work"),
    OTHER("Other"),
    ;
    private final String status;

    public String getStatus() {
        return status;
    }

    EmploymentStatus(final String status){
        this.status = status;
    }
}
