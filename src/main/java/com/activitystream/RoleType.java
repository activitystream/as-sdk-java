package com.activitystream;

public class RoleType {
    private final String actor;

    private RoleType(String actor) {
        this.actor = actor;
    }

    public String value(){
        return actor;
    }

    public RoleType extend(String ext){
        return new RoleType(actor + ":" + ext);
    }

    public static RoleType ACTOR = new RoleType("ACTOR");
    public static RoleType AFFECTS = new RoleType("AFFECTS");
    public static RoleType INVOLVES = new RoleType("INVOLVES");
    public static RoleType OBSERVED = new RoleType("OBSERVED");
    public static RoleType REFERENCES = new RoleType("REFERENCES");
}
