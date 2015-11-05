package com.activitystream.aspects;

import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;

import java.util.Map;
import java.util.Set;

public class Attachment {
    private final String url;
    private String fingerprint;
    private String filename;
    private String description;
    private Double size;
    private String created;
    private String updated;
    private Map<String, Double> metadata;
    private Map<String, String> properties;

    public Attachment(String url) {
        this.url = url;
    }

    public Attachment fingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    public Attachment filename(String filename) {
        this.filename = filename;
        return this;
    }

    public Attachment description(String description) {
        this.description = description;
        return this;
    }

    public Attachment size(Double size) {
        this.size = size;
        return this;
    }

    public Attachment created(String created) {
        DateHelpers.validateDateString(created);
        this.created = created;
        return this;
    }

    public Attachment updated(String updated) {
        DateHelpers.validateDateString(updated);
        this.updated = updated;
        return this;
    }

    public Attachment metadata(Map<String, Double> metadata) {
        this.metadata = metadata;
        return this;
    }

    public Attachment properties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public Map toJson() {
        Map result = Factories.getMap();
        result.put("url", url);
        result.put("description", description);
        result.put("filename", filename);
        result.put("fingerprint", fingerprint);
        result.put("size", size);
        result.put("created", created);
        result.put("updated", updated);
        result.put("metadata", metadata);
        result.put("properties", properties);
        return result;
    }
}
