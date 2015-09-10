package com.activitystream;

import java.util.*;

public class EventTestBase {
    public static Map obj(Object... keyValueStriped) {
        if (keyValueStriped.length % 2 != 0) throw new RuntimeException("This map does not look good");
        Map result = new HashMap();
        for (int i = 0; i < keyValueStriped.length; i += 2) {
            String key = (String) keyValueStriped[i];
            Object value = keyValueStriped[i+1];
            result.put(key, value);
        }
        return result;
    }

    public static List arr(Object ... items){
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, items);
        return arrayList;
    }

}
