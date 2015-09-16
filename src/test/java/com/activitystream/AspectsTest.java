package com.activitystream;

import com.activitystream.aspects.ClientDeviceAspect;
import com.activitystream.aspects.ClientIPAddressAspect;
import com.activitystream.aspects.PageviewAspect;
import com.activitystream.aspects.RequestMethod;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static com.activitystream.Predefined.*;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AspectsTest extends EventTestBase {
    public static Role PURCHASED(Entity ent) { return new Role("PURCHASED", ent);}

    @Test
    public void client_ip() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = event("action")
                .aspects(new ClientIPAddressAspect().clientIp("127.0.0.1"))
                .involves(ACTOR(entityRef(PERSON, "Petar")));

        Map expected = obj(
                "action", "action",
                "involves", arr(
                        obj(
                                "entity_ref", "Person/Petar",
                                "role", "ACTOR"
                        )
                ),
                "aspects", obj("client_ip", "127.0.0.1")
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void client_device() {
        HashMap props = new HashMap();
        props.put("favourite_programming_language", "javascript");
        Event ev = event("action")
                .aspects(new ClientDeviceAspect().clientDevice("iPhone"))
                .involves(ACTOR(entityRef(PERSON, "Petar")));

        Map expected = obj(
                "action", "action",
                "involves", arr(
                        obj(
                                "entity_ref", "Person/Petar",
                                "role", "ACTOR"
                        )
                ),
                "aspects", obj("client_device", "iPhone")
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }
    @Test
    public void ecommerce() {
        EntityType POI = new EntityType("Poi");
        Event ev = event("action")
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
        Map expected = obj(
            "action", "action",
            "aspects", obj(
                "items", arr(
                    obj(
                        "involves", arr(
                            obj(
                                "role", "PURCHASED",
                                "entity_ref", "Poi/12344542352345345"
                            )
                        ),
                        "item_count", 2,
                        "item_price", 15400.0,
                        "description", "desc",
                        "variant", "variant",
                        "price_category", "A",
                        "currency", "ISK",
                        "commission_fixed", 1540.0
                    )
                )
            )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void classification() {
        Event ev = event("action")
                .aspects(classificationAsepct()
                                .categories(new String[]{"Nature", "Waterfalls"})
                                .type("Poi")
                );

        Map expected = obj(
                "action", "action",
                "aspects", obj(
                        "classification", obj(
                            "action", "Poi",
                            "categories", arr("Nature", "Waterfalls")
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }
    @Test
    public void timed() throws ParseException {
        Event ev = event("action")
                .aspects(timedAspect()
                        .begins(DateHelpers.isoDateFormatter.parse("2015-11-24T17:00:00.000Z"))
                        .ends(DateHelpers.isoDateFormatter.parse("2015-11-24T20:00:00.000Z"))
                );

        Map expected = obj(
                "action", "action",
                "aspects", obj(
                        "timed", obj(
                                "begins", "2015-11-24T17:00:00.000Z",
                                "ends", "2015-11-24T20:00:00.000Z",
                                "action", "valid"
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void pageview() throws ParseException {
        Map referrerProps = new HashMap();
        referrerProps.put("a", "b");
        referrerProps.put("c", 22);
        Event ev = event("action")
                .aspects(new PageviewAspect()
                        .path("/path")
                        .pathProperties(null)
                        .keywords("a", "b", "c")
                        .method(RequestMethod.GET)
                        .referrer("http://localhost")
                        .referrerProperties(referrerProps)
                        .responseCode(200)
                        .size(100)
                        .protocol("HTTP")
                        .pageContent(rel().link(FEATURED, entityRef(PERSON, "Jane Doe")))
                );

        Map expected = obj(
                "action", "action",
                "aspects", obj(
                        "pageview", obj(
                                "path", "/path"
                                , "keywords", arr("a", "b", "c")
                                , "referrer", "http://localhost"
                                , "referrer_properties", obj("a", "b", "c", 22)
                                , "method", "GET"
                                , "response_code", 200
                                , "size", 100
                                , "protocol", "HTTP"
                                , "page_content", arr(
                                        obj("type", "FEATURED", "entity_ref", "Person/Jane Doe")
                                )
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }
}
