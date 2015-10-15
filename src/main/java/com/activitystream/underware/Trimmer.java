package com.activitystream.underware;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Trimmer {
    public static void trimMap(Map obj) {
        for (Iterator<Map.Entry<String, Object>> iterator = obj.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> entry = iterator.next();
            Object value = entry.getValue();
            trimmer(iterator, value);
        }
    }

    private static void trimmer(Iterator iterator, Object value) {
        if (value == null) {
            iterator.remove();
        } else if (List.class.isAssignableFrom(value.getClass())) {
            List listValue = (List) value;
            trimList(listValue);
            if (listValue.size() == 0) iterator.remove();
        } else if (Map.class.isAssignableFrom(value.getClass())) {
            Map listValue = (Map) value;
            trimMap(listValue);
            if (listValue.size() == 0) iterator.remove();
        }
    }

    private static void trimList(List value) {
        for (Iterator<Object> iterator = value.iterator(); iterator.hasNext(); ) {
            Object entry = iterator.next();
            trimmer(iterator, entry);
        }
    }
}
