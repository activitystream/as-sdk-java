package com.activitystream;

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
}
