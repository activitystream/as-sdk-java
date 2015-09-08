package com.activitystream;

import org.json.simple.JSONValue;

public class EntityType {
    private String type;

    public EntityType(String type){

        this.type = type;
    }

    public String toJson(){
        return type;
    }
    public static EntityType create(String type) {
        return new EntityType(type);
    }
    public static EntityType extend(EntityType base, String type){
        return new EntityType(base.type + ":" + type);
    }
}
