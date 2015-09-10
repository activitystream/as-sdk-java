package com.activitystream;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class EntityRef implements Entity {
    private EntityType type;
    private String id;

    public EntityRef id(EntityType type, String id){
        this.type = type;
        this.id = id;
        return this;
    }
    public EntityRef(EntityType type, String id){
        this.id(type, id);
    }
    public EntityRef(String ref){
        throw new NotImplementedException();
    }

    @Override
    public void addToObject(JsonObject jsonObject) {
        jsonObject.add("entity_ref", new JsonPrimitive(type.toJson() + "/" + id));
    }
}
