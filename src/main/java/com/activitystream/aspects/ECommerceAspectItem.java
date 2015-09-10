package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.Role;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Date;

public class ECommerceAspectItem {
    private Role[] involved = new Role[]{};
    private Aspect[] aspects = new Aspect[]{};

    public ECommerceAspectItem involves(Role... role){
        this.involved = role;
        return this;
    }

    public ECommerceAspectItem aspects(Aspect...aspects){
        this.aspects = aspects;
        return this;
    }

    private Double commissionFixed;

    public ECommerceAspectItem commissionFixed(Double commissionFixed) {
        this.commissionFixed = commissionFixed;
        return this;
    }

    private Integer itemCount;

    public ECommerceAspectItem itemCount(Integer itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    private Double itemPrice;

    public ECommerceAspectItem itemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    private String description;

    public ECommerceAspectItem description(String description) {
        this.description = description;
        return this;
    }

    private String variant;

    public ECommerceAspectItem variant(String variant) {
        this.variant = variant;
        return this;
    }

    private String priceCategory;

    public ECommerceAspectItem priceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
        return this;
    }

    private String currency;

    public ECommerceAspectItem currency(String currency) {
        this.currency = currency;
        return this;
    }


    public ECommerceAspectItem occured(Date timestamp){
        return this;
    }

    public ECommerceAspectItem properties(){
        return this;
    }

    public JsonObject toJson() {
        JsonObject obj=new JsonObject();

        if (involved.length > 0){
            JsonArray inv = new JsonArray();
            for (int i = 0; i < involved.length; i++) {
                inv.add(involved[i].toJson());
            }
            obj.add("involves", inv);
        }

        if (commissionFixed != null) obj.add("commission_fixed", new JsonPrimitive(commissionFixed));
        if (currency != null) obj.add("currency", new JsonPrimitive(currency));
        if (priceCategory != null) obj.add("price_category", new JsonPrimitive(priceCategory));
        if (variant != null) obj.add("variant", new JsonPrimitive(variant));
        if (description != null) obj.add("description", new JsonPrimitive(description));
        if (itemPrice != null) obj.add("item_price", new JsonPrimitive(itemPrice));
        if (itemCount != null) obj.add("item_count", new JsonPrimitive(itemCount));

        if (aspects.length > 0){
            JsonObject aspectsJson = new JsonObject();
            for (Aspect aspect : aspects){
                aspect.addToObject(aspectsJson);
            }
            obj.add("aspects", aspectsJson);
        }
        return obj;
    }
}
