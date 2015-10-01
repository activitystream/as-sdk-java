package com.activitystream;

import com.activitystream.aspects.ClientDeviceAspect;
import com.activitystream.aspects.ClientIPAddressAspect;
import com.activitystream.aspects.PageviewAspect;
import com.activitystream.aspects.RequestMethod;
import com.activitystream.helpers.DateHelpers;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static com.activitystream.Predefined.*;
import static com.activitystream.EntityRoleType.ACTOR;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AspectsTest extends EventTestBase {
    public static EntityRoleType PURCHASED = EntityRoleType.AFFECTS.extend("PURCHASED");

    @Test
    public void client_ip() {
        Event ev = event("action")
                .aspects(new ClientIPAddressAspect().clientIp("127.0.0.1"))
                .involves(role(ACTOR, entity(PERSON, "Petar")));

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
        Event ev = event("action")
                .aspects(new ClientDeviceAspect().clientDevice("iPhone"))
                .involves(role(ACTOR, entity(PERSON, "Petar")));

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
    public void ecommerce() throws ParseException {
        EntityType POI = new EntityType("Poi");
        EntityType Serial = new EntityType("Serial");
        Event ev = event("action")
                .aspects(eCommerce(
                                item()
                                        .involves(role(PURCHASED,entity(POI, "12344542352345345")))
                                        .commissionFixed(1540.0)
                                        .commissionPercentage(150D)
                                        .discountPercentage(15D)
                                        .taxPercentage(1.0)
                                        .itemCount(2)
                                        .totalInStock(2.5)
                                        .totalForSale(3.5)
                                        .itemPrice(15400.0)
                                        .serialNumbers(entity(Serial, "1234"), entity(Serial, "4567"))
                                        .validFrom(DateHelpers.isoDateFormatter.parse("2015-11-24T17:00:00.000Z"))
                                        .validUntil(DateHelpers.isoDateFormatter.parse("2015-11-24T18:00:00.000Z"))
                                        .description("desc")
                                        .variant("variant")
                                        .priceCategory("A")
                                        .currency("ISK")
                                        .accountingKey("KEY")
                        )
                );
        Map expected = obj(
            "action", "action",
            "aspects", obj(
                "items", arr(
                    obj(
                        "involves", arr(
                            obj(
                                "role", "AFFECTS:PURCHASED",
                                "entity_ref", "Poi/12344542352345345"
                            )
                        ),
                        "item_count", 2,
                        "item_price", 15400.0,
                        "serial_numbers", arr(obj("entity_ref", "Serial/1234"), obj("entity_ref", "Serial/4567")),
                        "description", "desc",
                        "variant", "variant",
                        "price_category", "A",
                        "currency", "ISK",
                        "commission_fixed", 1540.0,
                        "commission_percentage", 150.0,
                        "tax_percentage", 1.0,
                        "total_in_stock", 2.5,
                        "total_for_sale", 3.5,
                        "valid_from", "2015-11-24T17:00:00.000Z",
                        "valid_until", "2015-11-24T18:00:00.000Z",
                        "accounting_key", "KEY",
                        "discount_percentage", 15.0

                    )
                )
            )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void classification_aspect() {
        Event ev = event("action")
                .aspects(classification()
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
    public void timed_aspect() throws ParseException {
        Event ev = event("action")
                .aspects(timed()
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
                                .pathProperties(new HashMap())
                                .keywords("a", "b", "c")
                                .method(RequestMethod.GET)
                                .referrer("http://localhost")
                                .referrerProperties(referrerProps)
                                .responseCode(200)
                                .size(100)
                                .protocol("HTTP")
                                .pageContent(rel().link(FEATURED, entity(PERSON, "Jane Doe")))
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

    @Test
    public void location_aspect() {
        Event ev = event("action")
                .aspects(
                        location("32.790672,-96.81082").type("work")
                );

        Map expected = obj(
                "action", "action",
                "aspects", obj(
                        "location", obj(
                                "latlong", "32.790672,-96.81082",
                                "type", "work"
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void presentation_aspect() throws MalformedURLException {
        Event ev = event("action")
                .aspects(
                        presentation()
                                .label("label")
                                .thumbnail(new URL("http://microsoft.com"))
                                .description("desc")
                                .detailsUrl(new URL("http://google.com"))
                                .icon(new URL("http://apple.com"))
                );

        Map expected = obj(
                "action", "action",
                "aspects", obj(
                        "presentation", obj(
                                "label", "label",
                                "thumbnail", "http://microsoft.com",
                                "details_url", "http://google.com",
                                "icon", "http://apple.com",
                                "description", "desc"
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }
    @Test
    public void dimensions_aspect() throws MalformedURLException {
        Event ev = event("action")
                .aspects(
                        dimensions(mstr().key("key 1").value("val 1"))
                );

        Map expected = obj(
                "action", "action",
                "aspects", obj(
                        "dimensions", obj(
                                "key 1", "val 1"
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void metrics_aspect() throws MalformedURLException {
        Event ev = event("action")
                .aspects(
                        metrics(mdbl().key("key 1").value(1D))
                );

        Map expected = obj(
                "action", "action",
                "aspects", obj(
                        "metrics", obj(
                                "key 1", 1.0
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

}
