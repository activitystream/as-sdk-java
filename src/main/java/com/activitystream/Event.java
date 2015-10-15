package com.activitystream;

import com.activitystream.helpers.DateHelpers;
import com.activitystream.helpers.MapCreator;
import com.activitystream.underware.Factories;
import com.activitystream.underware.Trimmer;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Event {
    private static String sdkVersion;

    static {
        try {
            Properties prop = new Properties();
            InputStream in = Event.class.getResourceAsStream("/info.properties");
            prop.load(in);
            in.close();
            String version = prop.getProperty("version");
            if (!version.contains("$")) sdkVersion = "java-" + version;
            else sdkVersion = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private EventType event;
    private List<EntityRole> involved = new ArrayList<>();
    private List<Aspect> aspects = new ArrayList<>();
    private String timestamp;
    private String origin;
    private Map props;
    private String description;

    public Event(String type) { this.event = new EventType(type); }

    public Event(EventType type) {
        this.event = type;
    }

    public Event involves(EntityRole... role) {
        if (role != null) this.involved.addAll(Arrays.asList(role));
        return this;
    }

    public Event aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    public Event occurred(Date timestamp, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.timestamp = formatter.format(timestamp);
        return this;
    }

    public Event occurred(String timestamp) {
        DateHelpers.validateDateString(timestamp);
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
        Map obj = Factories.getMap();
        Set<String> processed = new HashSet<>();
        obj.put("type", event.id);
        obj.put("origin", origin);
        obj.put("description", description);
        obj.put("occurred_at", timestamp);

        if (involved.size() > 0) {
            List inv = new ArrayList();
            for (EntityRole anInvolved : involved) {
                if (anInvolved != null) {
                    inv.add(anInvolved.toJson(processed));
                }
            }
            obj.put("involves", inv);
        }

        if (aspects.size() > 0) {
            Map aspectsJson = Factories.getMap();
            for (Aspect aspect : aspects) {
                if (aspect != null) {
                    aspect.addToObject(aspectsJson, processed);
                }
            }
            obj.put("aspects", aspectsJson);
        }
        obj.put("properties", props);
        if (sdkVersion != null) obj.put("_v", sdkVersion);
        Trimmer.trimMap(obj);
        return obj;
    }

}
