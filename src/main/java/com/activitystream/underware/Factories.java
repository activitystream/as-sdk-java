package com.activitystream.underware;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Factories {
    public static <K,V> Map<K,V> getMap() {return new TreeMap<K,V>(Collections.reverseOrder());}
}
