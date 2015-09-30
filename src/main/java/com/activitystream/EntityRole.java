package com.activitystream;

import com.activitystream.helpers.MapCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * A role of a certain type of a given entity in an event
 */
public class EntityRole {
    private final String involvement;
    private final Entity ent;
    private Map props;

    public EntityRole(EntityRoleType involvment, Entity ent) {
        this.involvement = involvment.value();
        this.ent = ent;
    }

    public EntityRole properties(Map props) {
        this.props = props;
        return this;
    }

    public EntityRole properties(MapCreator props) {
        return properties(props.map());
    }


    public Map toJson() {
        Map obj = new HashMap();
        obj.put("role", involvement);
        if (props != null) obj.put("properties", props);
        ent.addToObject(obj);
        return obj;
    }
}
