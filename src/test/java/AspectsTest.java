import com.activitystream.Event;
import com.activitystream.EventType;
import com.activitystream.aspects.ClientDeviceAspect;
import com.activitystream.aspects.ClientIPAddressAspect;
import org.junit.Test;

import java.util.HashMap;

import static com.activitystream.Predefined.ACTOR;
import static com.activitystream.Predefined.PERSON;
import static com.activitystream.Sugar.entityRef;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AspectsTest extends EventTestBase {
    @Test
    public void client_ip() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .type(new EventType("type"))
                .aspects(new ClientIPAddressAspect().value("127.0.0.1"))
                .involves(ACTOR(entityRef(PERSON, "Petar")));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "            \"involves\" : [\n" +
                "                { \"role\": \"ACTOR\", \"entity_ref\" : \"Person/Petar\"\n" +
                "                }\n" +
                "            ], " +
                "            \"aspects\" : {" +
                "                   \"client_ip\": \"127.0.0.1\" " +
                "               }, " +
                "           \"event\" : \"type\"" +
                "        }")));
    }
    @Test
    public void client_device() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .type(new EventType("type"))
                .aspects(new ClientDeviceAspect().value("iPhone"))
                .involves(ACTOR(entityRef(PERSON, "Petar")));
        assertThat(ev.toJson(), equalTo(json("{\n" +
                "            \"involves\" : [\n" +
                "                { \"role\": \"ACTOR\", \"entity_ref\" : \"Person/Petar\"\n" +
                "                }\n" +
                "            ], " +
                "            \"aspects\" : {" +
                "                   \"client_device\": \"iPhone\" " +
                "               }, " +
                "           \"event\" : \"type\"" +
                "        }")));
    }
}
