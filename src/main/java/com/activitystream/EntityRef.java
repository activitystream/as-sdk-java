package com.activitystream;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class EntityRef implements EntityLike {
    private EntityType type;
    private String id;

    public EntityRef id(EntityType type, String id) {
        this.type = type;
        this.id = id;
        return this;
    }

    public EntityRef(EntityType type, String id) {
        this.id(type, id);
    }

    public EntityRef(String ref) {
        throw new NotImplementedException();
    }

    @Override
    public void addToObject(Map jsonObject) {
        jsonObject.put("entity_ref", type.toJson() + "/" + id);
    }
}
