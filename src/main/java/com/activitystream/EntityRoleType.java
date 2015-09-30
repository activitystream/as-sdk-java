package com.activitystream;

public class EntityRoleType {
    private final String actor;

    private EntityRoleType(String actor) {
        this.actor = actor;
    }

    public String value(){
        return actor;
    }

    public EntityRoleType extend(String ext){
        return new EntityRoleType(actor + ":" + ext);
    }

    public static EntityRoleType ACTOR = new EntityRoleType("ACTOR");
    public static EntityRoleType AFFECTS = new EntityRoleType("AFFECTS");
    public static EntityRoleType INVOLVES = new EntityRoleType("INVOLVES");
    public static EntityRoleType OBSERVED = new EntityRoleType("OBSERVED");
    public static EntityRoleType REFERENCES = new EntityRoleType("REFERENCES");
}
