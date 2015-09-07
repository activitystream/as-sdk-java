import com.activitystream.*;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.junit.Test;

import static com.activitystream.EntityType.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventTest {
    @Test
    public void should_create_an_event_with_id() {
        Event ev = new Event().id(new EventId("as.xcommerce.purchase.completed"));
        assertThat(ev.toString(), equalTo(json("{ \"event\" : \"as.xcommerce.purchase.completed\" }")));
    }
    @Test
    public void should_create_event_with_involved_actor_by_ref(){
        EntityType EMPLOYEE = create("Employee");
        Event ev = new Event().involves(new ACTOR(new EntityRef(EMPLOYEE, "Petar")));
        assertThat(ev.toString(), equalTo(json("{\n" +
                "            involves : [\n" +
                "                { \"ACTOR\" : \"Employee/Petar\"}\n" +
                "            ]                \n" +
                "        }")));
    }

    private String json(String json) {
        ObjectMapper mapper = JsonFactory.create();
        Object toJava = mapper.fromJson(json);
        return mapper.writeValueAsString(toJava);
    }
}
