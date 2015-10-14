package com.activitystream;

import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * The relation of a specific type between two entities
 */
public class EntityRelation {
    private EntityRelationType linkType;
    private Entity entity;
    private Map props;
    private String startDate;
    private String endDate;
    private Double weight;


    public EntityRelation link(EntityRelationType linkType, Entity entity) {
        this.linkType = linkType;
        this.entity = entity;
        return this;
    }

    public EntityRelation properties(Map props) {
        this.props = props;
        return this;
    }

    public EntityRelation validFrom(Date startDate, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.startDate = formatter.format(startDate);
        return this;
    }

    public EntityRelation validFrom(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.startDate = timestamp;
        return this;
    }

    public EntityRelation activeUntil(Date endDate, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.endDate = formatter.format(endDate);
        return this;
    }

    public EntityRelation activeUntil(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.endDate = timestamp;
        return this;
    }

    public EntityRelation weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Map toJson(Set<String> processed) {
        if (entity == null) throw new RuntimeException("relationship must have linked entity");

        Map obj = Factories.getMap();

        obj.put("type", linkType.value());
        if (weight != null) obj.put("weight", weight);
        if (startDate != null) obj.put("valid_from", startDate);
        if (endDate != null) obj.put("active_until", endDate);
        if (props != null) obj.put("properties", props);
        entity.addToObject(obj, processed);

        return obj;
    }

}
