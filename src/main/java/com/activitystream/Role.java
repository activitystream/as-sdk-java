package com.activitystream;

import java.util.HashMap;
import java.util.Map;

public class Role {
    private final String involvement;
    private final Entity ent;

    public Role(String involvement, Entity ent) {

        this.involvement = involvement;
        this.ent = ent;
    }

    public Map toJson() {
        Map obj = new HashMap();
        obj.put("role", involvement);
        ent.addToObject(obj);
        return obj;
    }
}
