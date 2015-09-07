package com.activitystream;

public class EventId {
    public String id;

    public EventId(String id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "EventID: "+id;
    }
}
