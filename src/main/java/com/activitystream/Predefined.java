package com.activitystream;

public class Predefined {
    public static EntityRoleType ACTOR = EntityRoleType.ACTOR;
    public static EntityRole role(EntityRoleType role, Entity entity){
        return new EntityRole(role, entity);
    }

    public static EntityRelationType AKA = new EntityRelationType("AKA");
    public static EntityRelationType MEMBER_OF = new EntityRelationType("MEMBER_OF");
    public static EntityRelationType PART_OF = new EntityRelationType("PART_OF");
    public static EntityRelationType PROXY_FOR = new EntityRelationType("PROXY_FOR");
    public static EntityRelationType BELONGS_TO = new EntityRelationType("BELONGS_TO");
    public static EntityRelationType SUPPLIED_BY = new EntityRelationType("SUPPLIED_BY");
    public static EntityRelationType LOCATED_AT = new EntityRelationType("LOCATED_AT");
    public static EntityRelationType FEATURED = new EntityRelationType("FEATURED");
}
