package com.activitystream.aspects;

import com.activitystream.EntityRole;
import com.activitystream.helpers.MapCreator;
import com.activitystream.underware.Factories;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MessagingAspect extends AspectBase {
    List<EntityRole> roles = new ArrayList<>();
    public MessagingAspect() {
        aspectPropertyMap.put("messaging.subject", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("messaging.properties", new AspectProperty(IsRequired.False, Factories.getMap()));
        aspectPropertyMap.put("messaging.involves", new AspectProperty(IsRequired.False, new ArrayList()));
        aspectPropertyMap.put("messaging.group", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("messaging.url", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("messaging.content", new AspectProperty(IsRequired.False));
    }

    public MessagingAspect involves(EntityRole... roles) {
        Collections.addAll(this.roles, roles);
        return this;
    }

    public MessagingAspect subject(String subject) {
        aspectPropertyMap.get("messaging.subject").value = subject;
        return this;
    }


    public MessagingAspect content(String content) {
        aspectPropertyMap.get("messaging.content").value = content;
        return this;
    }

    public MessagingAspect group(Boolean group) {
        aspectPropertyMap.get("messaging.group").value = group;
        return this;
    }

    public MessagingAspect properties(Map properties) {
        ((HashMap) aspectPropertyMap.get("messaging.properties").value).putAll(properties);
        return this;
    }

    public MessagingAspect properties(MapCreator properties) {
        return properties(properties.map());
    }

    public MessagingAspect url(URL url) {
        aspectPropertyMap.get("messaging.url").value = url.toString();
        return this;
    }

    public MessagingAspect url(String url) {
        try {
            return url(new URL(url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToObject(Map aspectJson, Set<String> processed) {
        List inv = new ArrayList();
        for (EntityRole anInvolved : roles) {
            if (anInvolved != null) inv.add(anInvolved.render(processed));
        }
        aspectPropertyMap.get("messaging.involves").value = inv;
        super.addToObject(aspectJson, processed);
    }
}
