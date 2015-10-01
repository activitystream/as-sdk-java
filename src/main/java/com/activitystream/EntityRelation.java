package com.activitystream;

import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;

import java.util.Date;
import java.util.Map;

/**
 * The relation of a specific type between two entities
 */
public class EntityRelation {
    private EntityRelationType linkType;
    private Entity entity;
    private Map props;
    private Date startDate;
    private Date endDate;
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

    public EntityRelation validFrom(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public EntityRelation activeUntil(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public EntityRelation weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Map toJson() {
        if (entity == null) throw new RuntimeException("relationship must have linked entity");

        Map obj = Factories.getMap();

        obj.put("type", linkType.toJson());
        if (weight != null) obj.put("weight", weight);
        if (startDate != null) obj.put("valid_from", DateHelpers.isoDateFormatter.format(startDate));
        if (endDate != null) obj.put("active_until", DateHelpers.isoDateFormatter.format(endDate));
        if (props != null) obj.put("properties", props);
        entity.addToObject(obj);

        return obj;
    }

}
