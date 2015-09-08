package com.activitystream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

public class Event {
    private EventId event;
    private Involved[] involved = new Involved[]{};

    public Event id(EventId id){
        this.event = id;
        return this;
    }
    public Event involves(Involved ... involved){
        this.involved = involved;
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
        return obj.toJSONString();
    }
}
