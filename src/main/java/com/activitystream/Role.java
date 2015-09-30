package com.activitystream;

import com.activitystream.helpers.MapCreator;

import java.util.HashMap;
import java.util.Map;

public class Role {
    private final String involvement;
    private final EntityLike ent;
    private Map props;

    public Role(RoleType involvment, EntityLike ent) {
        this.involvement = involvment.value();
        this.ent = ent;
    }

    public Role properties(Map props) {
        this.props = props;
        return this;
    }

    public Role properties(MapCreator props) {
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
