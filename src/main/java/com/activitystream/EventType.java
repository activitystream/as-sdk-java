package com.activitystream;

public class EventType {
    public String id;

    public EventType(String id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "EventID: " + id;
    }
}
