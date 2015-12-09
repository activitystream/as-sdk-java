package com.activitystream;

public class EntityRoleType {
    public static final EntityRoleType MESSAGING = new EntityRoleType("MESSAGING");
    public static final EntityRoleType SENT_TO = new EntityRoleType("SENT_TO");
    public static final EntityRoleType SENT_BY = new EntityRoleType("SENT_BY");
    public static final EntityRoleType SENT_TO_BCC = new EntityRoleType("SENT_TO_BCC");
    public static final EntityRoleType SENT_TO_CC = new EntityRoleType("SENT_TO_CC");


    public static final EntityRoleType TRADE = new EntityRoleType("TRADE");
    public static final EntityRoleType ACTOR = new EntityRoleType("ACTOR");
    public static final EntityRoleType AFFECTS = new EntityRoleType("AFFECTS");
    public static final EntityRoleType INVOLVES = new EntityRoleType("INVOLVES");
    public static final EntityRoleType OBSERVED = new EntityRoleType("OBSERVED");
    public static final EntityRoleType REFERENCES = new EntityRoleType("REFERENCES");
    private final String actor;

    private EntityRoleType(String actor) {
        this.actor = actor;
    }

    public String value() {
        return actor;
    }

    public EntityRoleType extend(String ext) {
        return new EntityRoleType(actor + ":" + ext);
    }
}
