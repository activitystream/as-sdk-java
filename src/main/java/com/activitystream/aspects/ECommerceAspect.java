package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ECommerceAspect implements Aspect {

    private ECommerceAspectItem[] items = new ECommerceAspectItem[]{};

    public ECommerceAspect items(ECommerceAspectItem... items) {
        this.items = items;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        if (items.length > 0) {
            List inv = new ArrayList();
            for (ECommerceAspectItem item : items) {
                inv.add(item.toJson(processed));
            }
            jsonObject.put("items", inv);
        }

    }
}
