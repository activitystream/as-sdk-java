package com.activitystream;

public class Predefined {
    public static final EntityRoleType ACTOR = EntityRoleType.ACTOR;
    public static final EntityRoleType AFFECTS = EntityRoleType.AFFECTS;
    public static final EntityRoleType INVOLVES = EntityRoleType.INVOLVES;
    public static final EntityRoleType OBSERVED = EntityRoleType.OBSERVED;
    public static final EntityRoleType REFERENCES = EntityRoleType.REFERENCES;
    public static final EntityRoleType TRADE = EntityRoleType.TRADE;
    public static final EntityRoleType MESSAGING = EntityRoleType.MESSAGING;
    public static final EntityRoleType CARTED = TRADE.extend("CARTED");
    public static final EntityRoleType UNCARTED = TRADE.extend("UNCARTED");
    public static final EntityRoleType PURCHASED = TRADE.extend("PURCHASED");
    public static final EntityRoleType CANCELLED = TRADE.extend("CANCELLED");
    public static final EntityRoleType RETURNED = TRADE.extend("RETURNED");
    public static final EntityRoleType SENT_TO = EntityRoleType.SENT_TO;
    public static final EntityRoleType SENT_TO_CC = EntityRoleType.SENT_TO_CC;
    public static final EntityRoleType SENT_TO_BCC = EntityRoleType.SENT_TO_BCC;

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

    public static CommentEntityRoleType COMMENTS = CommentEntityRoleType.COMMENTS;
    public static CommentEntityRoleType COMMENTED_ON = CommentEntityRoleType.COMMENTED_ON;
    public static CommentEntityRoleType MENTIONS = CommentEntityRoleType.MENTIONS;
}
