package com.activitystream;

public class Sugar {

    public static EntityRef entityRef(EntityType type, String id){return new EntityRef(type, id);}
    public static EntityEmbedded entityEmbedded(EntityType type, String id){return new EntityEmbedded().id(type, id);}
    public static EntityRelation rel(){return new EntityRelation();}
}
