package com.activitystream;

import com.activitystream.helpers.MapCreator;
import com.activitystream.underware.Factories;

import java.util.Map;
import java.util.Set;

/**
 * A role of a certain type of a given entity in an event
 */
public class EntityRole {
    private final String involvement;
    private final Entity ent;
    private Map props;
    private String externalId;

    public EntityRole(EntityRoleType involvement, Entity ent) {
        this.involvement = involvement.value();
        this.ent = ent;
    }

    public EntityRole properties(Map props) {
        this.props = props;
        return this;
    }

    public EntityRole properties(MapCreator props) {
        return properties(props.map());
    }

    public EntityRole externalId(String id) {
        this.externalId = id;
        return this;
    }

    public Map toJson(Set<String> processed) {
        Map obj = Factories.getMap();
        obj.put("role", involvement);
        obj.put("external_id", externalId);
        obj.put("properties", props);
        ent.addToObject(obj, processed);
        return obj;
    }
}
