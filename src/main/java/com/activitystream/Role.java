package com.activitystream;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Role {
    private final String involvement;
    private final Entity ent;

    public Role(String involvement, Entity ent) {

        this.involvement = involvement;
        this.ent = ent;
    }

    public JsonObject toJson() {
        JsonObject obj=new JsonObject();
        obj.add("role", new JsonPrimitive(involvement));
        ent.addToObject(obj);
        return obj;
    }
}
