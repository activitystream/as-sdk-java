package com.activitystream;

import com.activitystream.aspects.*;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashMap;

import static com.activitystream.Predefined.ACTOR;
import static com.activitystream.Predefined.PERSON;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AspectsTest extends EventTestBase {
    public static Role PURCHASED(Entity ent) { return new Role("PURCHASED", ent);}

    @Test
    public void client_ip() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .action(new EventType("action"))
                .aspects(new ClientIPAddressAspect().clientIp("127.0.0.1"))
                .involves(ACTOR(entityRef(PERSON, "Petar")));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "            \"involves\" : [\n" +
                "                { \"role\": \"ACTOR\", \"entity_ref\" : \"Person/Petar\"\n" +
                "                }\n" +
                "            ], " +
                "           \"action\": \"action\"," +
                "            \"aspects\" : {" +
                "                   \"client_ip\": \"127.0.0.1\" " +
                "               }" +
                "        }")));
    }

    @Test
    public void client_device() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .action(new EventType("action"))
                .aspects(new ClientDeviceAspect().clientDevice("iPhone"))
                .involves(ACTOR(entityRef(PERSON, "Petar")));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "            \"involves\" : [\n" +
                "                { \"role\": \"ACTOR\", \"entity_ref\" : \"Person/Petar\"\n" +
                "                }\n" +
                "            ], " +
                "           \"action\": \"action\"," +
                "            \"aspects\" : {" +
                "                   \"client_device\": \"iPhone\" " +
                "               } " +
                "        }")));
    }
    @Test
    public void ecommerce() {
        EntityType POI = new EntityType("Poi");
        Event ev = new Event()
                .action(new EventType("action"))
                .aspects(eCommerce(
                                item()
                                        .involves(PURCHASED(entityRef(POI, "12344542352345345")))
                                        .commissionFixed(1540.0)
                                        .itemCount(2)
                                        .itemPrice(15400.0)
                                        .description("desc")
                                        .variant("variant")
                                        .priceCategory("A")
                                        .currency("ISK")
                        )
                );
        assertThat(json(ev.toJson()), equalTo(json("" +
                "    {\n" +
                "        \"aspects\": {\n" +
                "            \"items\": [\n" +
                "                {\n" +
                "                    \"involves\": [\n" +
                "                        {\n" +
                "                            \"role\": \"PURCHASED\",\n" +
                "                            \"entity_ref\": \"Poi/12344542352345345\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"item_count\": 2,\n" +
                "                    \"item_price\": 15400.00,\n" +
                "                    \"description\": \"desc\",\n" +
                "                    \"variant\": \"variant\",\n" +
                "                    \"price_category\": \"A\",\n" +
                "                    \"currency\": \"ISK\",\n" +
                "                    \"commission_fixed\": 1540.0\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"action\": \"action\"\n" +
                "    }\n")));
    }

    @Test
    public void classification() {
        Event ev = new Event()
                .action(new EventType("action"))
                .aspects(classificationAsepct()
                                .categories(new String[]{"Nature", "Waterfalls"})
                                .type("Poi")
                );
        assertThat(ev.toJson(), equalTo(json("" +
                "    {\n" +
                "        \"action\": \"action\",\n" +
                "        \"aspects\": {\n" +
                "                    \"classification\": {\n" +
                "                        \"action\": \"Poi\",\n" +
                "                        \"categories\": [\n" +
                "                                \"Nature\",\n" +
                "                            \"Waterfalls\"\n" +
                "                        ]\n" +
                "                    }\n" +
                "        }\n" +
                "    }\n")));
    }
    @Test
    public void timed() throws ParseException {
        Event ev = new Event()
                .action(new EventType("action"))
                .aspects(timedAspect()
                        .begins(DateHelpers.isoDateFormatter.parse("2015-11-24T17:00:00.000Z"))
                        .ends(DateHelpers.isoDateFormatter.parse("2015-11-24T20:00:00.000Z"))
                );
        assertThat(ev.toJson(), equalTo(json("" +
                "    {\n" +
                "        \"action\": \"action\",\n" +
                "        \"aspects\": {\n" +
                "            \"timed\": {\n" +
                "                \"begins\": \"2015-11-24T17:00:00.000Z\",\n" +
                "                \"ends\": \"2015-11-24T20:00:00.000Z\",\n" +
                "                \"action\": \"valid\"\n" +
                "            }\n" +
                "    }\n" +
                "    }\n")));
    }


}
