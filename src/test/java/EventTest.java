import com.activitystream.*;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.util.HashMap;

import static com.activitystream.EntityType.create;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventTest {
    @Test
    public void should_create_an_event_with_id() {
        Event ev = new Event().id(new EventId("as.xcommerce.purchase.completed"));
        assertThat(ev.toJson(), equalTo(json("{ \"event\" : \"as.xcommerce.purchase.completed\" }")));
    }
    @Test
    public void should_create_event_with_involved_actor_by_ref(){
        EntityType EMPLOYEE = create("Employee");
        Event ev = new Event()
                .id(new EventId("id"))
                .involves(new ACTOR(new EntityRef(EMPLOYEE, "Petar")));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                " \"event\" : \"id\"," +
                "            \"involves\" : [\n" +
                "                { \"ACTOR\" : \"Employee/Petar\"}\n" +
                "            ]                \n" +
                "        }")));
    }

    @Test
    public void should_create_event_with_involved_embedded_actor(){
        EntityType PERSON = create("Person");
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .id(new EventId("id"))
                .involves(new ACTOR(new EntityEmbedded()
                        .id(PERSON, "Petar")
                        .properties(props)
                        .relations(
                                new EntityRelation()
                                        .link(LinkType.AKA(), new EntityRef(new EntityType("Email"), "pshomov@gmail.com")),
                                new EntityRelation()
                                        .link(LinkType.AKA(), new EntityRef(new EntityType("Twitter"), "pshomov")),
                                new EntityRelation()
                                        .link(LinkType.AKA(), new EntityRef(new EntityType("Building"), "Laugavegur 26"))
                        )
                ));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "            \"involves\" : [\n" +
                "                { \"ACTOR\" : \n" +
                "                    {\n" +
                "                        \"entity_ref\" : \"Person/Petar\",\n" +
                "                        \"properties\" : {\"favourite_programming_language\" : \"javascript\"},\n" +
                "                        \"relations\" : [\n" +
                "                            {\"AKA\" : \"Email/pshomov@gmail.com\"},\n" +
                "                            {\"AKA\" : \"Twitter/pshomov\"},\n" +
                "                            {\"AKA\" : \"Building/Laugavegur 26\"}\n" +
                "                        ]   \n" +
                "                    }\n" +
                "                }\n" +
                "            ],                \n" +
                "           \"event\" : \"id\""+
                "        }")));
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
