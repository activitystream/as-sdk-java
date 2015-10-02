package com.activitystream.underware;

import java.util.LinkedHashMap;
import java.util.Map;

public class Factories {
    public static <K,V> Map<K,V> getMap() {return new LinkedHashMap<>();}
}
