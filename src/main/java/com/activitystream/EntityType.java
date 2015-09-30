package com.activitystream;

/**
 * A strongly typed entity discriminator to avoid typos when creating entity
 */
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
