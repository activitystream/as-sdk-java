package com.activitystream;

import org.json.simple.JSONObject;
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
    public void addToObject(JSONObject jsonObject) {
        jsonObject.put("entity_ref", type.toJson() + "/" + id);
    }
}
