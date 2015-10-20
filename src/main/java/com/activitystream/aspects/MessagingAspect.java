package com.activitystream.aspects;

import com.activitystream.helpers.MapCreator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MessagingAspect extends AspectBase {
    public MessagingAspect() {
        aspectPropertyMap.put("messaging.subject", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("messaging.from", new AspectProperty(IsRequired.True));
        aspectPropertyMap.put("messaging.to", new AspectProperty(IsRequired.True, new ArrayList()));
        aspectPropertyMap.put("messaging.cc", new AspectProperty(IsRequired.False, new ArrayList()));
        aspectPropertyMap.put("messaging.bcc", new AspectProperty(IsRequired.False, new ArrayList()));
        aspectPropertyMap.put("messaging.properties", new AspectProperty(IsRequired.False, new HashMap<>()));
        aspectPropertyMap.put("messaging.group", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("messaging.url", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("messaging.content", new AspectProperty(IsRequired.False));
    }

    public MessagingAspect subject(String subject) {
        aspectPropertyMap.get("messaging.subject").value = subject;
        return this;
    }

    public MessagingAspect from(String from) {
        aspectPropertyMap.get("messaging.from").value = from;
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

    public MessagingAspect to(String... to) {
        Collections.addAll((Collection<? super String>) aspectPropertyMap.get("messaging.to").value, to);
        return this;
    }

    public MessagingAspect cc(String... cc) {
        Collections.addAll((Collection<? super String>) aspectPropertyMap.get("messaging.cc").value, cc);
        return this;
    }

    public MessagingAspect bcc(String... bcc) {
        Collections.addAll((Collection<? super String>) aspectPropertyMap.get("messaging.bcc").value, bcc);
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
}
