package com.activitystream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

public class Event {
    private EventType event;
    private Role[] involved = new Role[]{};
    private Aspect[] aspects = new Aspect[]{};

    public Event type(EventType id){
        this.event = id;
        return this;
    }
    public Event involves(Role... role){
        this.involved = role;
        return this;
    }

    public Event aspects(Aspect...aspects){
        this.aspects = aspects;
        return this;
    }

    public Event occured(Date timestamp){
        return this;
    }

    public Event properties(){
        return this;
    }

    public String toJson() {
        JSONObject obj=new JSONObject();
        obj.put("event", event.id);

        if (involved.length > 0){
            JSONArray inv = new JSONArray();
            for (int i = 0; i < involved.length; i++) {
                inv.add(involved[i].toJson());
            }
            obj.put("involves", inv);
        }

        if (aspects.length > 0){
            JSONObject aspectsJson = new JSONObject();
            for (Aspect aspect : aspects){
                aspect.addToObject(aspectsJson);
            }
            obj.put("aspects", aspectsJson);
        }
        return obj.toJSONString();
    }
}
