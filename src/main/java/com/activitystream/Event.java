package com.activitystream;

import com.google.gson.*;

import java.util.Date;
import java.util.Map;

public class Event {
    private EventType event;
    private Role[] involved = new Role[]{};
    private Aspect[] aspects = new Aspect[]{};
    private Date timestamp;
    private String origin;
    private Map props;

    public Event action(EventType type){
        this.event = type;
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
        this.timestamp = timestamp;
        return this;
    }

    public Event origin(String origin) {
        this.origin = origin;
        return this;
    }

    public Event properties(Map props){
        this.props = props;
        return this;
    }

    public String toJson() {
        JsonObject obj=new JsonObject();
        obj.add("action", new JsonPrimitive(event.id));

        if (involved.length > 0){
            JsonArray inv = new JsonArray();
            for (int i = 0; i < involved.length; i++) {
                inv.add(involved[i].toJson());
            }
            obj.add("involves", inv);
        }

        if (aspects.length > 0){
            JsonObject aspectsJson = new JsonObject();
            for (Aspect aspect : aspects){
                aspect.addToObject(aspectsJson);
            }
            obj.add("aspects", aspectsJson);
        }
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        if (props != null) obj.add("properties", parser.parse(gson.toJson(props)));
        if (origin != null) obj.add("origin", parser.parse(gson.toJson(origin)));
        if (timestamp != null) obj.add("occurred_at", new JsonPrimitive(DateHelpers.isoDateFormatter.format(timestamp)));
        return obj.toString();
    }
}
