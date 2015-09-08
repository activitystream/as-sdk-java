package com.activitystream;

import org.json.simple.JSONObject;

public class EntityRelation {
    private LinkType linkType;
    private Entity entity;

    public EntityRelation link(LinkType linkType, Entity entity) {
        this.linkType = linkType;
        this.entity = entity;
        return this;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        JSONObject jsonObject = entity.toJson();

        if ((boolean) jsonObject.get("byref")) {
            obj.put(linkType.toJson(), jsonObject.get("ref"));
        } else {
            obj.put(linkType.toJson(), jsonObject.get("val"));
        }

        return obj;
    }

}
