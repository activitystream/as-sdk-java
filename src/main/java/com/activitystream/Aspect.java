package com.activitystream;

import java.util.Map;
import java.util.Set;

public interface Aspect {
    void addToObject(Map jsonObject, Set<String> processed);
}
