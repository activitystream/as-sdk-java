package com.activitystream;

import com.activitystream.helpers.MapCreator;

import java.util.*;

/**
 * @deprecated Please use {@link Entity} instead
 */
@Deprecated
public class EntityEmbedded implements EntityLike {
    private EntityType type;
    private String id;
    private EntityRelation[] relations = new EntityRelation[]{};
    private Map props = new HashMap();
    private List<Aspect> aspects = new ArrayList<>();

    public EntityEmbedded id(EntityType type, String id) {
        this.type = type;
        this.id = id;
        return this;
    }

    public EntityEmbedded properties(Map props) {
        this.props = props;
        return this;
    }

    public EntityEmbedded properties(MapCreator props) {
        return properties(props.map());
    }

    public EntityEmbedded relations(EntityRelation... relation) {
        this.relations = relation;
        return this;
    }

    public EntityEmbedded aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    @Override
    public void addToObject(Map jsonObject) {
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
