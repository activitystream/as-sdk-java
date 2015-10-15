package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.*;

public class CommerceAspect implements Aspect {

    private List<CommerceAspectItem> items = new ArrayList<>();

    public CommerceAspect items(CommerceAspectItem... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        List inv = new ArrayList();
        for (CommerceAspectItem item : items) {
            if (item != null) inv.add(item.toJson(processed));
        }
        jsonObject.put("items", inv);
    }
}
