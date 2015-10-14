package com.activitystream;

public class EntityRoleType {
    public static final EntityRoleType TRADE = new EntityRoleType("TRADE");
    public static EntityRoleType ACTOR = new EntityRoleType("ACTOR");
    public static EntityRoleType AFFECTS = new EntityRoleType("AFFECTS");
    public static EntityRoleType INVOLVES = new EntityRoleType("INVOLVES");
    public static EntityRoleType OBSERVED = new EntityRoleType("OBSERVED");
    public static EntityRoleType REFERENCES = new EntityRoleType("REFERENCES");
    private final String actor;
    public static final EntityRoleType CARTED = TRADE.extend("CARTED");
    public static final EntityRoleType UNCARTED = TRADE.extend("UNCARTED");
    public static final EntityRoleType PURCHASED = TRADE.extend("PURCHASED");
    public static final EntityRoleType CANCELLED = TRADE.extend("CANCELLED");
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
