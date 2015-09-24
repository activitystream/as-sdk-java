package com.activitystream;

import com.activitystream.helpers.MapCreator;

import java.util.*;

public class Entity implements EntityLike {
    private EntityType type;
    private String id;
    private EntityRelation[] relations = new EntityRelation[]{};
    private Map props = new HashMap();
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
        this.relations = relation;
        return this;
    }

    public Entity aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    @Override
    public void addToObject(Map jsonObject) {
        if (relations.length == 0 && props.size() == 0 && aspects.size() == 0){
            jsonObject.put("entity_ref", type.toJson() + "/" + id);
        } else {
            Map value = new HashMap();
            value.put("entity_ref", type.toJson() + "/" + id);

            if (relations.length > 0) {
                List inv = new ArrayList();
                for (int i = 0; i < relations.length; i++) {
                    inv.add(relations[i].toJson());
                }
                value.put("relations", inv);
            }
            if (props.size() > 0) {
                value.put("properties", props);
            }
            if (aspects.size() > 0) {
                Map aspectsJson = new HashMap();
                for (Aspect aspect : aspects) {
                    aspect.addToObject(aspectsJson);
                }
                value.put("aspects", aspectsJson);
            }
            jsonObject.put("entity", value);
        }
    }
}
