package com.activitystream;

import org.json.simple.JSONObject;

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

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("byref", true);
        jsonObject.put("ref", type.toJson() + "/" + id);
        return jsonObject;
    }
}
