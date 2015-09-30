package com.activitystream;

import com.activitystream.helpers.DateHelpers;
import com.activitystream.helpers.MapCreator;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Event {
    private EventType event;
    private List<EntityRole> involved = new ArrayList<>();
    private List<Aspect> aspects = new ArrayList<>();
    private Date timestamp;
    private String origin;
    private Map props;
    private String description;

    public Event(String action) {
        this.event = new EventType(action);
    }

    public Event() {
    }

    @Deprecated()
    public Event action(EventType type) {
        this.event = type;
        return this;
    }

    public Event involves(EntityRole... role) {
        this.involved.addAll(Arrays.asList(role));
        return this;
    }

    public Event aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    /**
     * @deprecated typo in name, replaced by {@link #occurred(Date)}
     */
    public Event occured(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Event occurred(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Event origin(String origin) {
        this.origin = origin;
        return this;
    }

    public Event properties(Map props) {
        this.props = props;
        return this;
    }

    public Event properties(MapCreator props) {
        return properties(props.map());
    }

    public Event description(String description) {
        this.description = description;
        return this;
    }

    public String toJson() {
        return JSONObject.toJSONString(toMap()).replace("\\/", "/");
    }

    public Map toMap() {
        Map obj = new HashMap();
        obj.put("action", event.id);

        if (involved.size() > 0) {
            List inv = new ArrayList();
            for (EntityRole anInvolved : involved) {
                inv.add(anInvolved.toJson());
            }
            obj.put("involves", inv);
        }

        if (aspects.size() > 0) {
            Map aspectsJson = new HashMap();
            for (Aspect aspect : aspects) {
                aspect.addToObject(aspectsJson);
            }
            obj.put("aspects", aspectsJson);
        }
        if (props != null) obj.put("properties", props);
        if (origin != null) obj.put("origin", origin);
        if (description != null) obj.put("description", description);
        if (timestamp != null) obj.put("occurred_at", DateHelpers.isoDateFormatter.format(timestamp));
        try {
            Properties prop = new Properties();
            InputStream in = getClass().getResourceAsStream("/info.properties");
            prop.load(in);
            in.close();
            String version = prop.getProperty("version");
//            if (version != null) obj.put("sdk", "java-"+ version);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
