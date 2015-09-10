package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.Role;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    public JSONObject toJson() {
        JSONObject obj=new JSONObject();

        if (involved.length > 0){
            JSONArray inv = new JSONArray();
            for (int i = 0; i < involved.length; i++) {
                inv.add(involved[i].toJson());
            }
            obj.put("involves", inv);
        }

        if (commissionFixed != null) obj.put("commission_fixed", commissionFixed);
        if (currency != null) obj.put("currency", currency);
        if (priceCategory != null) obj.put("price_category", priceCategory);
        if (variant != null) obj.put("variant", variant);
        if (description != null) obj.put("description", description);
        if (itemPrice != null) obj.put("item_price", itemPrice);
        if (itemCount != null) obj.put("item_count", itemCount);

        if (aspects.length > 0){
            JSONObject aspectsJson = new JSONObject();
            for (Aspect aspect : aspects){
                aspect.addToObject(aspectsJson);
            }
            obj.put("aspects", aspectsJson);
        }
        return obj;
    }
}