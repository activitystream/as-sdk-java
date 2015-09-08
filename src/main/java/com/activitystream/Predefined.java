package com.activitystream;

public class Predefined {
    public static Role ACTOR(Entity ent) { return new Role("ACTOR", ent);}
    public static Role AFFECTS(Entity ent) { return new Role("AFFECTS", ent);}
    public static Role SOLD_TO(Entity ent) { return new Role("SOLD_TO", ent);}
    public static Role SOLD_BY(Entity ent) { return new Role("SOLD_BY", ent);}
    public static Role INVOLVES(Entity ent) { return new Role("INVOLVES", ent);}
    public static Role OBSERVED(Entity ent) { return new Role("OBSERVED", ent);}
    public static Role REFERENCES(Entity ent) { return new Role("REFERENCES", ent);}

    public static LinkType AKA = new LinkType("AKA");
    public static LinkType MEMBER_OF = new LinkType("MEMBER_OF");
    public static LinkType PART_OF = new LinkType("PART_OF");
    public static LinkType PROXY_FOR = new LinkType("PROXY_FOR");
    public static LinkType BELONGS_TO = new LinkType("BELONGS_TO");

    public static EntityType EMAIL = new EntityType("Email");
    public static EntityType TWITTER = new EntityType("Twitter");
    public static EntityType PERSON = new EntityType("Person");
    public static EntityType EMPLOYEE = new EntityType("Employee");


}
