package com.activitystream;

import com.activitystream.helpers.MapCreator;
import com.activitystream.underware.Factories;
import org.json.simple.JSONObject;

import java.util.*;

public class Entity {
    private EntityType type;
    private String id;
    private List<EntityRelation> relations = new ArrayList<>();
    private Map props = Factories.getMap();
    private List<Aspect> aspects = new ArrayList<>();

    public Entity(EntityType type, String id) {
        this.type = type;
        this.id = id;
    }

    public Entity properties(Map props) {
        this.props = props;
        return this;
    }

    public Entity properties(MapCreator props) {
        return properties(props.map());
    }

    public Entity relations(EntityRelation... relation) {
        this.relations.addAll(Arrays.asList(relation));
        return this;
    }

    public Entity aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    public void addToObject(Map jsonObject, Set<String> processed) {
        String entityId = type.toJson() + "/" + id;
        if ((relations.size() == 0 && props.size() == 0 && aspects.size() == 0) || processed.contains(this.toString())){
            jsonObject.put("entity_ref", entityId);
        } else {
            processed.add(this.toString());
            Map value = Factories.getMap();
            value.put("entity_ref", entityId);

            if (relations.size() > 0) {
                List inv = new ArrayList();
                for (EntityRelation relation : relations) {
                    inv.add(relation.toJson(processed));
                }
                value.put("relations", inv);
            }
            if (props.size() > 0) {
                value.put("properties", props);
            }
            if (aspects.size() > 0) {
                Map aspectsJson = Factories.getMap();
                for (Aspect aspect : aspects) {
                    aspect.addToObject(aspectsJson, processed);
                }
                value.put("aspects", aspectsJson);
            }
            jsonObject.put("entity", value);
        }
    }

    public String toJson() {
        return JSONObject.toJSONString(toMap()).replace("\\/", "/");
    }

    public Map toMap() {
        Map result = Factories.getMap();
        addToObject(result, new HashSet<String>());
        return result;
    }
}
