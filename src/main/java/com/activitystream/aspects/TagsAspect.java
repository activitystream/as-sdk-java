package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.*;

public class TagsAspect implements Aspect {
    private List<String> tags = new ArrayList<>();

    public TagsAspect(String ... tags) {
        Collections.addAll(this.tags, tags);
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        if (tags.size() > 0) jsonObject.put("tags", tags);
    }
}
