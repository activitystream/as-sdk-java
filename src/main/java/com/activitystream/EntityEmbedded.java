package com.activitystream;

import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;

public class EntityEmbedded implements Entity {
    private EntityType type;
    private String id;
    private EntityRelation[] relations = new EntityRelation[]{};
    private Map props = new HashMap();

    public EntityEmbedded id(EntityType type, String id){
        this.type = type;
        this.id = id;
        return this;
    }
    public EntityEmbedded properties(Map props){
        this.props = props;
        return this;
    }

    public EntityEmbedded relations(EntityRelation... relation) {
        this.relations = relation;
        return this;
    }

    @Override
    public void addToObject(JsonObject jsonObject) {
        JsonObject value = new JsonObject();
        value.add("entity_ref", new JsonPrimitive(type.toJson() + "/" + id));

        if (relations.length > 0){
            JsonArray inv = new JsonArray();
            for (int i = 0; i < relations.length; i++) {
                inv.add(relations[i].toJson());
            }
            value.add("relations", inv);
        }
        if (props.size() > 0){
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            value.add("properties", parser.parse(gson.toJson(props)));
        }
        jsonObject.add("entity", value);
    }
}
