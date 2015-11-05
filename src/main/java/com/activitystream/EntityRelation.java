package com.activitystream;

import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;
import com.activitystream.underware.Tuple;

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
    private String externalId;


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

    public EntityRelation externalId(String id) {
        this.externalId = id;
        return this;
    }

    public Map render(Set<String> processed) {
        if (entity == null) throw new RuntimeException("relationship must have linked entity");

        final Tuple<String, Object> entityTuple = entity.render(processed);
        if (entityTuple != null) {
            Map obj = Factories.getMap();

            obj.put("type", linkType.value());
            obj.put("external_id", externalId);
            obj.put("weight", weight);
            obj.put("valid_from", startDate);
            obj.put("active_until", endDate);
            obj.put("properties", props);
            obj.put(entityTuple.x, entityTuple.y);
            return obj;
        }

        return null;
    }
}
