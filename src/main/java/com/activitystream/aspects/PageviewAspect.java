package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.EntityRelation;

import java.util.*;

public class PageviewAspect implements Aspect {
    private String path;

    public PageviewAspect path(String path) {
        this.path = path;
        return this;
    }

    private Map pathProps;

    public PageviewAspect pathProperties(Map pathProps) {
        this.pathProps = pathProps;
        return this;
    }

    private List keywords;

    public PageviewAspect keywords(String ... keywords) {
        this.keywords = new ArrayList();
        Collections.addAll(this.keywords, keywords);
        return this;
    }

    private RequestMethod method;

    public PageviewAspect method(RequestMethod method) {
        this.method = method;
        return this;
    }

    private Integer responseCode;

    public PageviewAspect responseCode(Integer responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    private Integer size;

    public PageviewAspect size(Integer size) {
        this.size = size;
        return this;
    }

    private String protocol;

    public PageviewAspect protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    private List<EntityRelation> pageContent;

    public PageviewAspect pageContent(EntityRelation ... pageContent) {
        this.pageContent = new ArrayList<>();
        Collections.addAll(this.pageContent, pageContent);
        return this;
    }

    private String referrer;

    public PageviewAspect referrer(String referrer) {
        this.referrer = referrer;
        return this;
    }

    private Map referrerProperties;

    public PageviewAspect referrerProperties(Map referrerProperties) {
        this.referrerProperties = referrerProperties;
        return this;
    }

    @Override
    public void addToObject(Map jsonObject) {
        Map result = new HashMap();
        if (path != null) result.put("path", path);
        if (pathProps != null) result.put("path_properties", pathProps);
        if (keywords != null) result.put("keywords", keywords);
        if (method != null) result.put("method", method.toString());
        if (responseCode != null) result.put("response_code", responseCode);
        if (size != null) result.put("size", size);
        if (protocol != null) result.put("protocol", protocol);
        if (referrer != null) result.put("referrer", referrer);
        if (referrerProperties != null) result.put("referrer_properties", referrerProperties);
        if (pageContent != null) {
            List pageContentItems = new ArrayList();
            for (EntityRelation entityRelation : pageContent) {
                pageContentItems.add(entityRelation.toJson());
            }
            result.put("page_content", pageContentItems);
        }
        jsonObject.put("pageview", result);
    }
}
