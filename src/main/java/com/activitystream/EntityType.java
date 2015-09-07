package com.activitystream;

public class EntityType {
    private String type;

    public EntityType(String type){

        this.type = type;
    }
    public static EntityType create(String type) {
        return new EntityType(type);
    }
    public static EntityType extend(EntityType base, String type){
        return new EntityType(base.type + ":" + type);
    }
}
