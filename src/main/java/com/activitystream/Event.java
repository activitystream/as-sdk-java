package com.activitystream;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

import java.util.Date;

class ASEvent {

    public String event;
}
public class Event {
    private EventId event;
    private ASEvent ev = new ASEvent();

    public Event id(EventId id){
        this.event = id;
        ev.event = id.id;
        return this;
    }
    public Event involves(Involved ... involved){
        return this;
    }

    public Event occured(Date timestamp){
        return this;
    }

    public Event properties(){
        return this;
    }

    @Override
    public String toString() {
        ObjectMapper mapper =  JsonFactory.create();
        return mapper.writeValueAsString(ev);
    }
}
