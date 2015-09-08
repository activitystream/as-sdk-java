package com.activitystream;

public class Predefined {
    public static Involved ACTOR(Entity ent) { return new Involved("ACTOR", ent);}
    public static Involved AFFECTS(Entity ent) { return new Involved("AFFECTS", ent);}
    public static Involved INVOLVES(Entity ent) { return new Involved("INVOLVES", ent);}
    public static Involved OBSERVED(Entity ent) { return new Involved("OBSERVED", ent);}
    public static Involved REFERENCES(Entity ent) { return new Involved("REFERENCES", ent);}

    public static LinkType AKA= new LinkType("AKA");
    public static LinkType MEMBER_OF = new LinkType("MEMBER_OF");
    public static LinkType PART_OF = new LinkType("PART_OF");
    public static LinkType PROXY_FOR = new LinkType("PROXY_FOR");
    public static LinkType BELONGS_TO = new LinkType("BELONGS_TO");
}
