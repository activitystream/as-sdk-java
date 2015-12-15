package com.activitystream.aspects.demography;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    UNKNOWN("Unknown"),
    TRANS_FEMALE("Trans Female"),
    TRANS_MALE("Trans Male"),
    TRANS_PERSON("Trans Person"),
    GENDER_VARIANT("Gender Variant"),
    GENDER_QUESTIONING("Gender Questioning"),
    BIGENDER("Bigender"),
    ANDROGYNOUS("Androgynous"),
    PANGENDER("Pangender"),
    TRANSSEXUAL("Transsexual"),
    ;
    private final String gender;

    public String getGender() {
        return gender;
    }

    Gender(final String gender){
        this.gender = gender;
    }
}

