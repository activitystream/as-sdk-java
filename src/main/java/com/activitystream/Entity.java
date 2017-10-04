package com.activitystream;

import com.activitystream.helpers.DateHelpers;
import com.activitystream.helpers.MapCreator;
import com.activitystream.underware.Factories;
import com.activitystream.underware.Trimmer;
import com.activitystream.underware.Tuple;
import com.activitystream.underware.Version;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class Entity implements TransformableToJson {

    private EntityType type;
    private String id;
    private String timestamp;
    private List<EntityRelation> relations = new ArrayList<>();
    private Map props = Factories.getMap();
    private List<Aspect> aspects = new ArrayList<>();
    
    private String partition;

    private String ASMessageType = "as.api.entity";
    
    private String header;

    public Entity() {
        throw new UnsupportedOperationException("Entity requires an id and a type.");
    }

    public Entity(EntityType type, String id) {
        this.type = type;
        this.id = id;
    }
    public Entity preprocess(){
         this.ASMessageType = "as.api.entity.preprocess";
         return this;
    }
    public Entity occurred(Date timestamp, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.timestamp = formatter.format(timestamp);
        return this;
    }

    public Entity occurred(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.timestamp = timestamp;
        return this;
    }

    public Entity properties(Map props) {
        this.props = props;
        return this;
    }

    public Entity properties(MapCreator props) {
        return properties(props.map());
    }

    public Entity relations(EntityRelation... relation) {
        this.relations.addAll(Arrays.asList(relation));
        return this;
    }

    public Entity aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    public Entity partition(String partition){
        this.partition = partition;
        return this;
    }
    
    public Entity header(String header){
        this.header = header;
        return this;
    }

    public Tuple<String, Object> render(Set<String> processed) {
        if (id == null || id.trim().isEmpty()) return null;
        String entityId = type.toJson() + "/" + id;
        if ((relations.size() == 0 && props.size() == 0 && aspects.size() == 0) || processed.contains(this.toString())) {
            return new Tuple<>("entity_ref", (Object) entityId);
        } else {
            processed.add(this.toString());
            Map value = Factories.getMap();
            value.put("entity_ref", entityId);

            List inv = new ArrayList();
            for (EntityRelation relation : relations) {
                if (relation != null)
                    inv.add(relation.render(processed));
            }
            value.put("relations", inv);
            if (props.size() > 0) {
                value.put("properties", props);
            }
            if (aspects.size() > 0) {
                Map aspectsJson = Factories.getMap();
                for (Aspect aspect : aspects) {
                    if (aspect != null)
                        aspect.addToObject(aspectsJson, processed);
                }
                value.put("aspects", aspectsJson);
            }
            if (partition != null) {
                value.put("partition", partition);
            }
            if (header != null) {
                value.put("_tid", header);
            }
            return new Tuple<>("entity", (Object) value);
        }
    }
    
    @Override
    public String toJson() {
        return JSONObject.toJSONString(toMap()).replace("\\/", "/");
    }

    public Map toMap() {
        Map map = Factories.getMap();
        final Tuple<String, Object> render = render(new HashSet<String>());
        if (render.x.equals("entity")) {
            map = (Map) render.y;
        } else {
            map.put(render.x, render.y);
        }

        map.put("type", this.ASMessageType);
        if (timestamp != null)
            map.put("occurred_at", timestamp);
        map.put("_v", Version.sdkVersion);
        Trimmer.trimMap(map);
        return map;
    }
}
