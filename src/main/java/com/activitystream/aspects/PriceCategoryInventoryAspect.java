package com.activitystream.aspects;

import com.activitystream.underware.Factories;

import java.util.Map;

public class PriceCategoryInventoryAspect extends AspectBase {

    private String priceCategory;
    private Double itemsInStock;
    private Double itemsForSale;
    private Double itemsSold;

    public PriceCategoryInventoryAspect() {
        /*
        aspectPropertyMap.put("inventory.category_inventory.price_category",new AspectProperty(IsRequired.True));
        aspectPropertyMap.put("inventory.category_inventory.items_in_stock", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.category_inventory.items_for_sale", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("inventory.category_inventory.items_sold", new AspectProperty(IsRequired.False));
        */
    }

    public PriceCategoryInventoryAspect priceCategory(String priceCategory){
        //aspectPropertyMap.get("inventory.category_inventory.price_category").value = priceCategory;
        this.priceCategory = priceCategory;
        return this;
    }
    public PriceCategoryInventoryAspect itemsInStock(Double itemsInStock) {
        //aspectPropertyMap.get("inventory.category_inventory.items_in_stock").value = itemsInStock;
        this.itemsInStock = itemsInStock;
        return this;
    }

    public PriceCategoryInventoryAspect itemsForSale(Double itemsForSale) {
        //aspectPropertyMap.get("inventory.category_inventory.items_for_sale").value = itemsForSale;
        this.itemsForSale = itemsForSale;
        return this;
    }

    public PriceCategoryInventoryAspect itemsSold(Double itemsSold) {
        //aspectPropertyMap.get("inventory.category_inventory.items_sold").value = itemsSold;
        this.itemsSold = itemsSold;
        return this;
    }

    public Map toJson() {
        Map result = Factories.getMap();
        result.put("price_category", priceCategory);
        result.put("items_in_stock", itemsInStock);
        result.put("items_for_sale", itemsForSale);
        result.put("items_sold", itemsSold);
        return result;
    }
}