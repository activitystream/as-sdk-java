package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ECommerceAspect implements Aspect {

    private ECommerceAspectItem[] items = new ECommerceAspectItem[]{};

    public ECommerceAspect items(ECommerceAspectItem... items) {
        this.items = items;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject) {
        if (items.length > 0) {
            List inv = new ArrayList();
            for (ECommerceAspectItem item : items) {
                inv.add(item.toJson());
            }
            jsonObject.put("items", inv);
        }

    }
}
