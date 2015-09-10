package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.EventType;
import com.activitystream.Role;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

public class ECommerceAspect implements Aspect{

    private ECommerceAspectItem[] items = new ECommerceAspectItem[]{};

    public ECommerceAspect items(ECommerceAspectItem... items) {
        this.items = items;
        return this;
    }

    @Override
    public void addToObject(JSONObject jsonObject) {
        if (items.length > 0){
            JSONArray inv = new JSONArray();
            for (ECommerceAspectItem item : items) {
                inv.add(item.toJson());
            }
            jsonObject.put("items", inv);
        }

    }
}
