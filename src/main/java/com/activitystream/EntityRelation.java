package com.activitystream;

import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class EntityRelation {
    private LinkType linkType;
    private Entity entity;
    private Map props;
    private Date startDate;
    private Date endDate;
    private Double weight;
    private static DateFormat isoDateFormatter;

    static {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        isoDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        isoDateFormatter.setTimeZone(tz);
    }


    public EntityRelation link(LinkType linkType, Entity entity) {
        this.linkType = linkType;
        this.entity = entity;
        return this;
    }

    public EntityRelation properties(Map props){
        this.props = props;
        return this;
    }
    public EntityRelation validFrom(Date startDate){
        this.startDate = startDate;
        return this;
    }
    public EntityRelation activeUntil(Date endDate){
        this.endDate = endDate;
        return this;
    }
    public EntityRelation weight(Double weight){
        this.weight = weight;
        return this;
    }

    public JSONObject toJson() {
        if (entity == null) throw new RuntimeException("relationship must have linked entity");

        JSONObject obj = new JSONObject();

        obj.put("type", linkType.toJson());
        if (weight != null) obj.put("weight", weight);
        if (startDate != null) obj.put("valid_from", isoDateFormatter.format(startDate));
        if (endDate != null) obj.put("active_until", endDate);
        if (props != null) obj.put("properties", props);
        entity.addToObject(obj);

        return obj;
    }

}
