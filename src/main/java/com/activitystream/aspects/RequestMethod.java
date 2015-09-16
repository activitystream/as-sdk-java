package com.activitystream.aspects;

public enum RequestMethod {
    GET, POST, DELETE, PUT;
    @Override
    public String toString() {
        if (this == GET) return "GET";
        if (this == POST) return "POST";
        if (this == PUT) return "PUT";
        if (this == DELETE) return "DELETE";
        throw new RuntimeException("unhandled action of timed action");
    }
}
