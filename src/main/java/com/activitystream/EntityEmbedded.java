package com.activitystream;

import org.json.simple.JSONObject;

public class EntityEmbedded implements Entity {
    private EntityType type;
    private String id;
    private EntityRelation[] relation;

    public EntityEmbedded id(EntityType type, String id){
        this.type = type;
        this.id = id;
        return this;
    }
    public EntityEmbedded properties(){
        return this;
    }

    public EntityEmbedded relations(EntityRelation... relation) {
        this.relation = relation;
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("byref", true);
        jsonObject.put("ref", type.toJson() + "/" + id);
        return jsonObject;
    }
}
