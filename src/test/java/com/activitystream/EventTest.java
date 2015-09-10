package com.activitystream;

import com.activitystream.aspects.AddressAspect;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static com.activitystream.Predefined.*;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.fail;

public class EventTest extends EventTestBase {

    public static final EntityType BUILDING = new EntityType("Building");

    @Test
    public void should_create_an_event_with_id() {
        Event ev = new Event().action(new EventType("as.xcommerce.purchase.completed"));
        assertThat(ev.toJson(), equalTo(json("{ \"action\" : \"as.xcommerce.purchase.completed\" }")));
    }

    @Test
    public void should_create_event_with_involved_actor_by_ref() {
        Event ev = new Event()
                .action(new EventType("action"))
                .involves(ACTOR(entityRef(EMPLOYEE, "Petar")));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                " \"action\": \"action\"," +
                "            \"involves\" : [\n" +
                "                { \"role\" : \"ACTOR\", \"entity_ref\" : \"Employee/Petar\"}\n" +
                "            ]                \n" +
                "        }")));
    }

    @Test
    public void should_create_event_with_involved_embedded_actor() throws ParseException {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .action(new EventType("action"))
                .involves(ACTOR(entityEmbedded(PERSON, "Petar")
                                .properties(props)
                                .relations(
                                        rel().link(AKA, entityRef(EMAIL, "pshomov@gmail.com")).validFrom(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2014-12-01T10:00:00")),
                                        rel().link(AKA, entityRef(TWITTER, "pshomov")),
                                        rel().link(AKA, entityRef(BUILDING, "Laugavegur 26"))
                                )
                ));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "           \"action\": \"action\"," +
                "            \"involves\" : [\n" +
                "                { \"role\": \"ACTOR\", \"entity\" : \n" +
                "                    {\n" +
                "                        \"entity_ref\" : \"Person/Petar\",\n" +
                "                        \"relations\" : [\n" +
                "                            {\"action\": \"AKA\", \"valid_from\" : \"2014-12-01T10:00:00.000Z\", \"entity_ref\" : \"Email/pshomov@gmail.com\"},\n" +
                "                            {\"action\": \"AKA\", \"entity_ref\" : \"Twitter/pshomov\"},\n" +
                "                            {\"action\": \"AKA\", \"entity_ref\" : \"Building/Laugavegur 26\"}\n" +
                "                        ],   \n" +
                "                        \"properties\" : {\"favourite_programming_language\" : \"javascript\"}\n" +
                "                    }\n" +
                "                }\n" +
                "            ]                \n" +
                "        }")));
    }
    @Test
    public void should_create_event_with_all_attributes() throws ParseException {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .action(new EventType("action"))
                .origin("browserX")
                .properties(props)
                .occured(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2014-12-01T10:00:00"));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "           \"action\": \"action\"," +
                "                        \"properties\" : {\"favourite_programming_language\" : \"javascript\"},\n" +
                "           \"origin\":\"browserX\"," +
                "           \"occurred_at\" : \"2014-12-01T10:00:00.000Z\"" +
                "        }")));
    }
    @Test
    public void should_create_event_with_aspects() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .action(new EventType("action"))
                .aspects(new AddressAspect().city("Reykjavík").countryCode("IS").line2("").streetAndNumber("Laugavegur 26").zipCode("2400"))
                .involves(ACTOR(entityRef(PERSON, "Petar")));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "           \"action\": \"action\"," +
                "            \"involves\" : [\n" +
                "                { \"role\": \"ACTOR\", \"entity_ref\" : \"Person/Petar\"\n" +
                "                }\n" +
                "            ], " +
                "            \"aspects\" : {" +
                "               \"address\": {" +
                "                   \"country_code\": \"IS\", " +
                "                   \"city\" :\"Reykjavík\"," +
                "                   \"address2\" : \"\"," +
                "                    \"zip_code\" : \"2400\"," +
                "                    \"address\" : \"Laugavegur 26\"" +
                "               } " +

                "            }   \n" +
                "        }")));
    }

    @Test
    public void should_not_allow_relationship_with_no_linked_entity() {
        Event ev = new Event()
                .action(new EventType("action"))
                .involves(ACTOR(entityEmbedded(PERSON, "Petar")
                                .relations(
                                        rel()
                                )
                ));
        try {
            ev.toJson();
            fail("that should not have been possible");
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), containsString("linked entity"));
        }
    }

}
