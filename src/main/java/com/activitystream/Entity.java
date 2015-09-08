package com.activitystream;

import org.json.simple.JSONObject;

public class Entity {
    private EntityType type;
    private String id;

    public Entity id(EntityType type, String id){
        this.type = type;
        this.id = id;
        return this;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("byref", true);
        jsonObject.put("ref", type.toJson() + "/" + id);
        return jsonObject;
    }
}
