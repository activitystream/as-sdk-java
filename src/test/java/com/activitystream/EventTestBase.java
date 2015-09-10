package com.activitystream;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Map;

public class EventTestBase {
    protected String json(String json) {
        JsonParser parser = new JsonParser();
        JsonElement parsed = parser.parse(json);
        return parsed.toString();
    }
}
