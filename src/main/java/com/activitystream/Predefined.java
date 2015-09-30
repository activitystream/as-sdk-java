package com.activitystream;

public class Predefined {
    public static RoleType ACTOR = RoleType.ACTOR;
    public static Role role(RoleType role, EntityLike entity){
        return new Role(role, entity);
    }

    public static LinkType AKA = new LinkType("AKA");
    public static LinkType MEMBER_OF = new LinkType("MEMBER_OF");
    public static LinkType PART_OF = new LinkType("PART_OF");
    public static LinkType PROXY_FOR = new LinkType("PROXY_FOR");
    public static LinkType BELONGS_TO = new LinkType("BELONGS_TO");
    public static LinkType SUPPLIED_BY = new LinkType("SUPPLIED_BY");
    public static LinkType LOCATED_AT = new LinkType("LOCATED_AT");
    public static LinkType FEATURED = new LinkType("FEATURED");

    public static EntityType EMAIL = new EntityType("Email");
    public static EntityType TWITTER = new EntityType("Twitter");
    public static EntityType PERSON = new EntityType("Person");
    public static EntityType EMPLOYEE = new EntityType("Employee");


}
