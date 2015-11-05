package com.activitystream;

import com.activitystream.helpers.DateHelpers;
import com.activitystream.helpers.MapCreator;
import com.activitystream.underware.Factories;
import com.activitystream.underware.Trimmer;
import com.activitystream.underware.Version;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class Comment {

    private final String comment;
    private List<CommentEntityRole> involved = new ArrayList<>();
    private List<Aspect> aspects = new ArrayList<>();
    private String timestamp;
    private String origin;

    public Comment(String comment) { this.comment = comment; }

    public Comment involves(CommentEntityRole... role) {
        if (role != null) this.involved.addAll(Arrays.asList(role));
        return this;
    }

    public Comment aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    public Comment occurred(Date timestamp, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.timestamp = formatter.format(timestamp);
        return this;
    }

    public Comment occurred(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.timestamp = timestamp;
        return this;
    }

    public Comment origin(String origin) {
        this.origin = origin;
        return this;
    }

    public String toJson() {
        return JSONObject.toJSONString(toMap()).replace("\\/", "/");
    }

    public Map toMap() {
        Map obj = Factories.getMap();
        Set<String> processed = new HashSet<>();
        obj.put("type", "as.api.comment");
        obj.put("origin", origin);
        obj.put("occurred_at", timestamp);
        obj.put("comment", comment);

        List inv = new ArrayList();
        for (CommentEntityRole anInvolved : involved) {
            if (anInvolved != null) {
                inv.add(anInvolved.render(processed));
            }
        }
        obj.put("involves", inv);

        Map aspectsJson = Factories.getMap();
        for (Aspect aspect : aspects) {
            if (aspect != null) {
                aspect.addToObject(aspectsJson, processed);
            }
        }
        obj.put("aspects", aspectsJson);
        obj.put("_v", Version.sdkVersion);
        Trimmer.trimMap(obj);
        return obj;
    }

}
