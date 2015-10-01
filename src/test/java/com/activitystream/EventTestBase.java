package com.activitystream;

import com.activitystream.underware.Factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EventTestBase {
    public static EntityType EMAIL = new EntityType("Email");
    public static EntityType TWITTER = new EntityType("Twitter");
    public static EntityType PERSON = new EntityType("Person");
    public static EntityType EMPLOYEE = new EntityType("Employee");


    public static Map obj(Object... keyValueStriped) {
        if (keyValueStriped.length % 2 != 0) throw new RuntimeException("This map does not look good");
        Map result = Factories.getMap();
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
