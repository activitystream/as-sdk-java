package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ECommerceAspect implements Aspect{

    private ECommerceAspectItem[] items = new ECommerceAspectItem[]{};

    public ECommerceAspect items(ECommerceAspectItem... items) {
        this.items = items;
        return this;
    }

    @Override
    public void addToObject(JsonObject jsonObject) {
        if (items.length > 0){
            JsonArray inv = new JsonArray();
            for (ECommerceAspectItem item : items) {
                inv.add(item.toJson());
            }
            jsonObject.add("items", inv);
        }

    }
}
