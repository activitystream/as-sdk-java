package com.activitystream;

import com.activitystream.helpers.MapCreator;
import com.activitystream.underware.Factories;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Event {
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

    private static String sdkVersion;

    static {
        try {
            Properties prop = new Properties();
            InputStream in = Event.class.getResourceAsStream("/info.properties");
            prop.load(in);
            in.close();
            String version = prop.getProperty("version");
            if (!version.contains("$")) sdkVersion = "java-"+version; else sdkVersion = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Event involves(EntityRole... role) {
        this.involved.addAll(Arrays.asList(role));
        return this;
    }

    public Event aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    public Event occurred(Date timestamp, TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        formatter.setTimeZone(timeZone);

        this.timestamp =  formatter.format(timestamp);
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
        if (origin != null) obj.put("origin", origin);
        if (description != null) obj.put("description", description);
        if (timestamp != null) obj.put("occurred_at", timestamp);

        if (involved.size() > 0) {
            List inv = new ArrayList();
            for (EntityRole anInvolved : involved) {
                inv.add(anInvolved.toJson(processed));
            }
            obj.put("involves", inv);
        }

        if (aspects.size() > 0) {
            Map aspectsJson = Factories.getMap();
            for (Aspect aspect : aspects) {
                aspect.addToObject(aspectsJson, processed);
            }
            obj.put("aspects", aspectsJson);
        }
        if (props != null) obj.put("properties", props);
        if (sdkVersion != null) obj.put("_v", sdkVersion);
        return obj;
    }
}
