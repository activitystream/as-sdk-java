package com.activitystream;

public class EntityType {
    private String type;

    public EntityType(String type) {

        this.type = type;
    }

    public String toJson() {
        return type;
    }

    public EntityType extend(String type) {
        return new EntityType(this.type + ":" + type);
    }
}
