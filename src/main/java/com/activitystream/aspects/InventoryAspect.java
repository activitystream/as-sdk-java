package com.activitystream.aspects;

import java.util.*;

public class InventoryAspect extends AspectBase {

    //private List<PriceCategoryInventoryAspect> items = new ArrayList<>();
    private List items = new ArrayList<>();

    public InventoryAspect() {
        aspectPropertyMap.put("inventory.items_in_stock", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.items_for_sale", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.items_sold", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.items_reserved", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.items_unsellable", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.items_complimentary", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.price_categories", new AspectProperty(IsRequired.False));
    }

    public InventoryAspect itemsInStock(Double itemsInStock) {
        aspectPropertyMap.get("inventory.items_in_stock").value = itemsInStock;
        return this;
    }

    public InventoryAspect itemsReserved(Double itemsReserved) {
        aspectPropertyMap.get("inventory.items_reserved").value = itemsReserved;
        return this;
    }

    public InventoryAspect itemsForSale(Double itemsForSale) {
        aspectPropertyMap.get("inventory.items_for_sale").value = itemsForSale;
        return this;
    }

    /**
     * @deprecated - Only here for backwards compatibility - use itemsUnsellable instead
     * TODO: Kill this when the integration platform stops using this.
     */
    public InventoryAspect itemsUnavailable(Double itemsUnavailable) {
        return this.itemsUnsellable(itemsUnavailable);
    }

    public InventoryAspect itemsUnsellable(Double itemsUnsellable) {
        aspectPropertyMap.get("inventory.items_unsellable").value = itemsUnsellable;
        return this;
    }

    public InventoryAspect itemsSold(Double itemsSold) {
        aspectPropertyMap.get("inventory.items_sold").value = itemsSold;
        return this;
    }

    public InventoryAspect itemsComplimentary(double itemsComplimentary) {
        aspectPropertyMap.get("inventory.items_complimentary").value = itemsComplimentary;
        return this;
    }
    
    public InventoryAspect addPriceCategory(PriceCategoryInventoryAspect pc) {
        this.items.add(pc.toJson());
        aspectPropertyMap.get("inventory.price_categories").value = this.items;
        return this;
    }
    
}
