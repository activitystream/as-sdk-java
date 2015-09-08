package com.activitystream;

import org.json.simple.JSONObject;

public class Involved {
    private final String involvement;
    private final Entity ent;

    public Involved(String involvement, Entity ent) {

        this.involvement = involvement;
        this.ent = ent;
    }

    public JSONObject toJson() {
        JSONObject obj=new JSONObject();
        obj.put("role", involvement);
        ent.addToObject(obj);
        return obj;
    }
}
