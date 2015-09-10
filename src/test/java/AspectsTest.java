import com.activitystream.*;
import com.activitystream.aspects.ClientDeviceAspect;
import com.activitystream.aspects.ClientIPAddressAspect;
import com.activitystream.aspects.ECommerceAspect;
import com.activitystream.aspects.ECommerceAspectItem;
import org.junit.Test;

import java.util.HashMap;

import static com.activitystream.Predefined.ACTOR;
import static com.activitystream.Predefined.PERSON;
import static com.activitystream.Sugar.entityRef;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AspectsTest extends EventTestBase {
    public static Role PURCHASED(Entity ent) { return new Role("PURCHASED", ent);}

    @Test
    public void client_ip() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = new Event()
                .type(new EventType("type"))
                .aspects(new ClientIPAddressAspect().clientIp("127.0.0.1"))
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
                .aspects(new ClientDeviceAspect().clientDevice("iPhone"))
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
    @Test
    public void ecommerce() {
        EntityType POI = new EntityType("Poi");
        Event ev = new Event()
                .type(new EventType("type"))
                .aspects(new ECommerceAspect().items(new ECommerceAspectItem()
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
                "        \"event\": \"type\",\n" +
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
                "        }\n" +
                "    }\n")));
    }


}
