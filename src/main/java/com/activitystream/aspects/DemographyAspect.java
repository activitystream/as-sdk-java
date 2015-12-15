package com.activitystream.aspects;

public class DemographyAspect extends AspectBase{

    public DemographyAspect() {
        aspectPropertyMap.put("demography.gender", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.birth_year", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.birth_month", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.birth_day", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.marital_status", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.education", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.family_size", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.employment", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.housing", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.income", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.disability", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("demography.ethnicity", new AspectProperty(IsRequired.False));
    }

    public DemographyAspect gender(Gender gender) {
        aspectPropertyMap.get("demography.gender").value = gender.getGender();
        return this;
    }

    public DemographyAspect gender(String gender) {
        aspectPropertyMap.get("demography.gender").value = gender;
        return this;
    }

    public DemographyAspect birthYear(Integer birthYear) {
        aspectPropertyMap.get("demography.birth_year").value = birthYear;
        return this;
    }

    public DemographyAspect birthMonth(Integer birthMonth) {
        aspectPropertyMap.get("demography.birth_month").value = birthMonth;
        return this;
    }

    public DemographyAspect birthDay(Integer birthDay) {
        aspectPropertyMap.get("demography.birth_day").value = birthDay;
        return this;
    }

    public DemographyAspect maritalStatus(String maritalStatus) {
        aspectPropertyMap.get("demography.marital_status").value = maritalStatus;
        return this;
    }

    public DemographyAspect maritalStatus(MaritalStatus maritalStatus) {
        aspectPropertyMap.get("demography.marital_status").value = maritalStatus.getMaritalStatus();
        return this;
    }

    public DemographyAspect education(String education) {
        aspectPropertyMap.get("demography.education").value = education;
        return this;
    }

    public DemographyAspect familySize(Integer familySize) {
        aspectPropertyMap.get("demography.family_size").value = familySize;
        return this;
    }

    public DemographyAspect employment(String employment) {
        aspectPropertyMap.get("demography.employment").value = employment;
        return this;
    }

    public DemographyAspect housing(String housing) {
        aspectPropertyMap.get("demography.housing").value = housing;
        return this;
    }

    public DemographyAspect income(String income) {
        aspectPropertyMap.get("demography.income").value = income;
        return this;
    }

    public DemographyAspect disability(String disability) {
        aspectPropertyMap.get("demography.disability").value = disability;
        return this;
    }

    public DemographyAspect ethnicity(String ethnicity) {
        aspectPropertyMap.get("demography.ethnicity").value = ethnicity;
        return this;
    }
}
