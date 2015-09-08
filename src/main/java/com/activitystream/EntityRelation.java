package com.activitystream;

import org.json.simple.JSONObject;

import java.util.Date;
import java.util.Map;

public class EntityRelation {
    private LinkType linkType;
    private Entity entity;

    public EntityRelation link(LinkType linkType, Entity entity) {
        this.linkType = linkType;
        this.entity = entity;
        return this;
    }

    public EntityRelation properties(Map props){
        return this;
    }
    public EntityRelation activeFrom(Date startDate){
        return this;
    }
    public EntityRelation activeUntil(Date endDate){
        return this;
    }
    public EntityRelation weight(Integer weight){
        return this;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        if (entity == null) throw new RuntimeException("relationship must have linked entity");

        obj.put("type", linkType.toJson());
        entity.addToObject(obj);

        return obj;
    }

}
