package com.activitystream.aspects;

public class InventoryAspect extends AspectBase {

    public InventoryAspect() {
        aspectPropertyMap.put("inventory.items_in_stock", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.items_for_sale", new AspectProperty(IsRequired.False));
    }

    public InventoryAspect itemsInStock(Double itemsInStock) {
        aspectPropertyMap.get("inventory.items_in_stock").value = itemsInStock;
        return this;
    }

    public InventoryAspect itemsForSale(Double itemsForSale) {
        aspectPropertyMap.get("inventory.items_for_sale").value = itemsForSale;
        return this;
    }
}
