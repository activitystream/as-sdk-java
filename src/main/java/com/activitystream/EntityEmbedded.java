package com.activitystream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONObject value = new JSONObject();
        value.put("entity_ref", type.toJson() + "/" + id);

        if (relations.length > 0){
            JSONArray inv = new JSONArray();
            for (int i = 0; i < relations.length; i++) {
                inv.add(relations[i].toJson());
            }
            value.put("relations", inv);
        }
        if (props.size() > 0){
            value.put("properties", props);
        }

        jsonObject.put("byref", false);
        jsonObject.put("val", value);
        return jsonObject;
    }
}
