package com.activitystream;

import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTestBase {
    protected String json(String json) {
        try {
            ContainerFactory orderedKeyFactory = new ContainerFactory()
            {
                public Map createObjectContainer() {
                    return new HashMap();
                }

                @Override
                public List creatArrayContainer() {
                    return new ArrayList();
                }

            };
            JSONParser parser = new JSONParser();
            Object parsed = parser.parse(json,orderedKeyFactory);
            return JSONValue.toJSONString(parsed).replace("\\/", "/");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
