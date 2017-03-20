package com.activitystream.aspects;

public class PriceCategoryInventoryAspect extends AspectBase {

    public PriceCategoryInventoryAspect() {
        aspectPropertyMap.put("inventory.category_inventory.price_category",new AspectProperty(IsRequired.True));
        aspectPropertyMap.put("inventory.category_inventory.items_in_stock", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.category_inventory.items_for_sale", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.category_inventory.items_sold", new AspectProperty(IsRequired.False));
    }

    public PriceCategoryInventoryAspect priceCategory(String priceCategory){
        aspectPropertyMap.get("inventory.category_inventory.price_category").value = priceCategory;
        return this;
    }
    public PriceCategoryInventoryAspect itemsInStock(Double itemsInStock) {
        aspectPropertyMap.get("inventory.category_inventory.items_in_stock").value = itemsInStock;
        return this;
    }

    public PriceCategoryInventoryAspect itemsForSale(Double itemsForSale) {
        aspectPropertyMap.get("inventory.category_inventory.items_for_sale").value = itemsForSale;
        return this;
    }

    public PriceCategoryInventoryAspect itemsSold(Double itemsSold) {
        aspectPropertyMap.get("inventory.category_inventory.items_sold").value = itemsSold;
        return this;
    }
}