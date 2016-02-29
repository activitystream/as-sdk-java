package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.EntityRelation;
import com.activitystream.underware.Factories;

import java.util.*;

public class PageviewAspect implements Aspect {
    private String path;
    private Map pathProps = Factories.getMap();
    private List keywords = new ArrayList();
    private RequestMethod method = RequestMethod.GET;
    private Integer responseCode;
    private Integer size;
    private String protocol;
    private List<EntityRelation> pageContent = new ArrayList<>();
    private String referrer;
    private Map referrerProperties;
    private String title;
    private String type;
    private List categories = new ArrayList();

    public PageviewAspect(String url) {
        path = url;
    }

    public PageviewAspect pathProperties(Map pathProps) {
        this.pathProps = pathProps;
        return this;
    }

    public PageviewAspect keywords(String... keywords) {
        Collections.addAll(this.keywords, keywords);
        return this;
    }

    public PageviewAspect method(RequestMethod method) {
        this.method = method;
        return this;
    }

    public PageviewAspect responseCode(Integer responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public PageviewAspect size(Integer size) {
        this.size = size;
        return this;
    }

    public PageviewAspect protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public PageviewAspect pageContent(EntityRelation... pageContent) {
        Collections.addAll(this.pageContent, pageContent);
        return this;
    }

    public PageviewAspect referrer(String referrer) {
        this.referrer = referrer;
        return this;
    }

    public PageviewAspect referrerProperties(Map referrerProperties) {
        this.referrerProperties = referrerProperties;
        return this;
    }

    public PageviewAspect title(String title) {
        this.title = title;
        return this;
    }

    public PageviewAspect type(String type) {
        this.type = type;
        return this;
    }

    public PageviewAspect categories(String... categories) {
        Collections.addAll(this.categories, categories);
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        Map result = Factories.getMap();
        result.put("path", path);
        if (pathProps != null) result.put("path_properties", pathProps);
        result.put("keywords", keywords);
        result.put("method", method.toString());
        result.put("response_code", responseCode);
        result.put("size", size);
        result.put("protocol", protocol);
        result.put("title", title);
        result.put("type", type);
        result.put("categories", categories);
        result.put("referrer", referrer);
        result.put("referrer_properties", referrerProperties);
        List pageContentItems = new ArrayList();
        for (EntityRelation entityRelation : pageContent) {
            if (entityRelation != null) pageContentItems.add(entityRelation.render(processed));
        }
        result.put("page_content", pageContentItems);
        jsonObject.put("pageview", result);
    }
}
