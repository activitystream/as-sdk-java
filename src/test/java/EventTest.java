import com.activitystream.*;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

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
