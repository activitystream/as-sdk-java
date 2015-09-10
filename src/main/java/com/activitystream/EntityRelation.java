package com.activitystream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.Date;
import java.util.Map;

public class EntityRelation {
    private LinkType linkType;
    private Entity entity;
    private Map props;
    private Date startDate;
    private Date endDate;
    private Double weight;


    public EntityRelation link(LinkType linkType, Entity entity) {
        this.linkType = linkType;
        this.entity = entity;
        return this;
    }

    public EntityRelation properties(Map props){
        this.props = props;
        return this;
    }
    public EntityRelation validFrom(Date startDate){
        this.startDate = startDate;
        return this;
    }
    public EntityRelation activeUntil(Date endDate){
        this.endDate = endDate;
        return this;
    }
    public EntityRelation weight(Double weight){
        this.weight = weight;
        return this;
    }

    public JsonObject toJson() {
        if (entity == null) throw new RuntimeException("relationship must have linked entity");

        JsonObject obj = new JsonObject();

        obj.add("action", new JsonPrimitive(linkType.toJson()));
        if (weight != null) obj.add("weight", new JsonPrimitive(weight));
        if (startDate != null) obj.add("valid_from", new JsonPrimitive(DateHelpers.isoDateFormatter.format(startDate)));
        if (endDate != null) obj.add("active_until", new JsonPrimitive(DateHelpers.isoDateFormatter.format(endDate)));
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        if (props != null) obj.add("properties", parser.parse(gson.toJson(props)));
        entity.addToObject(obj);

        return obj;
    }

}
