package com.activitystream;

import org.json.simple.JSONObject;

import java.util.*;

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
        return JSONObject.toJSONString(toMap()).replace("\\/", "/");
    }
    public Map toMap() {
        Map obj=new HashMap();
        obj.put("action", event.id);

        if (involved.length > 0){
            List inv = new ArrayList();
            for (int i = 0; i < involved.length; i++) {
                inv.add(involved[i].toJson());
            }
            obj.put("involves", inv);
        }

        if (aspects.length > 0){
            Map aspectsJson = new HashMap();
            for (Aspect aspect : aspects){
                aspect.addToObject(aspectsJson);
            }
            obj.put("aspects", aspectsJson);
        }
        if (props != null) obj.put("properties", props);
        if (origin != null) obj.put("origin", origin);
        if (timestamp != null) obj.put("occurred_at", DateHelpers.isoDateFormatter.format(timestamp));
        return obj;
    }
}
