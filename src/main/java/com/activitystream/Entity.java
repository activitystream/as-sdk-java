package com.activitystream;

import com.activitystream.helpers.MapCreator;
import com.activitystream.underware.Factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Entity {
    private EntityType type;
    private String id;
    private List<EntityRelation> relations = new ArrayList<>();
    private Map props = Factories.getMap();
    private List<Aspect> aspects = new ArrayList<>();

    public Entity id(EntityType type, String id) {
        this.type = type;
        this.id = id;
        return this;
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

    public void addToObject(Map jsonObject) {
        if (relations.size() == 0 && props.size() == 0 && aspects.size() == 0){
            jsonObject.put("entity_ref", type.toJson() + "/" + id);
        } else {
            Map value = Factories.getMap();
            value.put("entity_ref", type.toJson() + "/" + id);

            if (relations.size() > 0) {
                List inv = new ArrayList();
                for (EntityRelation relation : relations) {
                    inv.add(relation.toJson());
                }
                value.put("relations", inv);
            }
            if (props.size() > 0) {
                value.put("properties", props);
            }
            if (aspects.size() > 0) {
                Map aspectsJson = Factories.getMap();
                for (Aspect aspect : aspects) {
                    aspect.addToObject(aspectsJson);
                }
                value.put("aspects", aspectsJson);
            }
            jsonObject.put("entity", value);
        }
    }
}
