package com.activitystream;

public class EntityEmbedded extends Entity {

    public Entity properties(){
        return this;
    }

    public Entity relations(EntityRelation relation) {
        return this;
    }
}
