package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommerceAspect implements Aspect {

    private CommerceAspectItem[] items = new CommerceAspectItem[]{};

    public CommerceAspect items(CommerceAspectItem... items) {
        this.items = items;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        if (items.length > 0) {
            List inv = new ArrayList();
            for (CommerceAspectItem item : items) {
                inv.add(item.toJson(processed));
            }
            jsonObject.put("items", inv);
        }

    }
}
