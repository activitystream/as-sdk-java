package com.activitystream;

import com.activitystream.aspects.ECommerceAspect;
import com.activitystream.aspects.ECommerceAspectItem;

public class Sugar {

    public static EntityRef entityRef(EntityType type, String id){return new EntityRef(type, id);}
    public static EntityEmbedded entityEmbedded(EntityType type, String id){return new EntityEmbedded().id(type, id);}
    public static EntityRelation rel(){return new EntityRelation();}
    public static ECommerceAspect eCommerce(ECommerceAspectItem ... items) {return new ECommerceAspect().items(items);}
    public static ECommerceAspectItem item() {return new ECommerceAspectItem();}
}
