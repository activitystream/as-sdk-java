import com.activitystream.EntityType;
import com.activitystream.Event;
import com.activitystream.EventId;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.util.HashMap;

import static com.activitystream.Predefined.*;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class EventTest {

    public static final EntityType BUILDING = new EntityType("Building");

    @Test
    public void should_create_an_event_with_id() {
        Event ev = new Event().id(new EventId("as.xcommerce.purchase.completed"));
        assertThat(ev.toJson(), equalTo(json("{ \"event\" : \"as.xcommerce.purchase.completed\" }")));
    }

    @Test
    public void should_create_event_with_involved_actor_by_ref() {
        Event ev = new Event()
                .id(new EventId("id"))
                .involves(ACTOR(entityRef(EMPLOYEE, "Petar")));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                " \"event\" : \"id\"," +
                "            \"involves\" : [\n" +
                "                { \"role\" : \"ACTOR\", \"entity_ref\" : \"Employee/Petar\"}\n" +
                "            ]                \n" +
                "        }")));
    }

    @Test
    public void should_create_event_with_involved_embedded_actor() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .id(new EventId("id"))
                .involves(ACTOR(entityEmbedded(PERSON, "Petar")
                                .properties(props)
                                .relations(
                                        rel().link(AKA, entityRef(EMAIL, "pshomov@gmail.com")),
                                        rel().link(AKA, entityRef(TWITTER, "pshomov")),
                                        rel().link(AKA, entityRef(BUILDING, "Laugavegur 26"))
                                )
                ));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "            \"involves\" : [\n" +
                "                { \"role\": \"ACTOR\", \"entity\" : \n" +
                "                    {\n" +
                "                        \"entity_ref\" : \"Person/Petar\",\n" +
                "                        \"properties\" : {\"favourite_programming_language\" : \"javascript\"},\n" +
                "                        \"relations\" : [\n" +
                "                            {\"type\": \"AKA\", \"entity_ref\" : \"Email/pshomov@gmail.com\"},\n" +
                "                            {\"type\": \"AKA\", \"entity_ref\" : \"Twitter/pshomov\"},\n" +
                "                            {\"type\": \"AKA\", \"entity_ref\" : \"Building/Laugavegur 26\"}\n" +
                "                        ]   \n" +
                "                    }\n" +
                "                }\n" +
                "            ],                \n" +
                "           \"event\" : \"id\"" +
                "        }")));
    }

    @Test
    public void should_not_allow_relationship_with_no_linked_entity() {
        Event ev = new Event()
                .id(new EventId("id"))
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

    private String json(String json) {
        try {
            Object parsed = null;
            parsed = new JSONParser().parse(json);
            return JSONValue.toJSONString(parsed);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
