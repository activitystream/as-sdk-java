package com.activitystream;

public class Predefined {
    public static EntityRoleType ACTOR = EntityRoleType.ACTOR;
    public static EntityRoleType AFFECTS = EntityRoleType.AFFECTS;
    public static EntityRoleType INVOLVES = EntityRoleType.INVOLVES;
    public static EntityRoleType OBSERVED = EntityRoleType.OBSERVED;
    public static EntityRoleType REFERENCES = EntityRoleType.REFERENCES;
    public static EntityRoleType TRADE = EntityRoleType.TRADE;
    public static EntityRoleType CARTED = EntityRoleType.CARTED;
    public static EntityRoleType UNCARTED = EntityRoleType.UNCARTED;
    public static EntityRoleType PURCHASED = EntityRoleType.PURCHASED;
    public static EntityRoleType CANCELLED = EntityRoleType.CANCELLED;

    public static EntityRelationType AKA = new EntityRelationType("AKA");
    public static EntityRelationType MEMBER_OF = new EntityRelationType("MEMBER_OF");
    public static EntityRelationType PART_OF = new EntityRelationType("PART_OF");
    public static EntityRelationType PROXY_FOR = new EntityRelationType("PROXY_FOR");
    public static EntityRelationType BELONGS_TO = new EntityRelationType("BELONGS_TO");
    public static EntityRelationType SUPPLIED_BY = new EntityRelationType("SUPPLIED_BY");
    public static EntityRelationType RELATED_TO = new EntityRelationType("RELATED_TO");
    public static EntityRelationType ASSOCIATED_WITH = new EntityRelationType("ASSOCIATED_WITH");
    public static EntityRelationType ON_BEHALF_OF = new EntityRelationType("ON_BEHALF_OF");
    public static EntityRelationType HAS_RELATIONS_TO = new EntityRelationType("HAS_RELATIONS_TO");
    public static EntityRelationType LOCATED_AT = new EntityRelationType("LOCATED_AT");
    public static EntityRelationType FEATURED = new EntityRelationType("FEATURED");
    public static EntityRelationType HOSTED_AT = new EntityRelationType("HOSTED_AT");
    public static EntityRelationType MANUFACTURED_BY = new EntityRelationType("MANUFACTURED_BY");
}
