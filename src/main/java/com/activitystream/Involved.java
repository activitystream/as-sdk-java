package com.activitystream;

import org.json.simple.JSONObject;

public class Involved {
    private final String involvement;
    private final Entity ent;

    public Involved(String involvement, Entity ent) {

        this.involvement = involvement;
        this.ent = ent;
    }

    public static Involved ACTOR(Entity ent){
       return new Involved("ACTOR", ent);
    }

    public JSONObject toJson() {
        JSONObject obj=new JSONObject();
        JSONObject jsonObject = ent.toJson();
        if ((boolean)jsonObject.get("byref")){
            obj.put(involvement, jsonObject.get("ref"));
        } else {
            obj.put(involvement, jsonObject.get("val"));
        }

        return obj;
    }
}
