package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.*;

public class ItemsAspect implements Aspect {

    private List<AspectItem> items = new ArrayList<>();

    public ItemsAspect(AspectItem... items) {
        add(items);
    }

    public ItemsAspect add(AspectItem... items) {
        Collections.addAll(this.items, items);
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        List inv = new ArrayList();
        for (AspectItem item : items) {
            if (item != null) inv.add(item.toJson(processed));
        }
        jsonObject.put("items", inv);
    }
}
