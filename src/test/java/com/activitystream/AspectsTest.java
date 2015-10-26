package com.activitystream;

import com.activitystream.aspects.ClientDeviceAspect;
import com.activitystream.aspects.ClientIPAddressAspect;
import com.activitystream.aspects.RequestMethod;
import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Map;
import java.util.TimeZone;

import static com.activitystream.EntityRoleType.ACTOR;
import static com.activitystream.Predefined.*;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AspectsTest extends EventTestBase {
    public static final EntityRoleType MESSAGING_CC = MESSAGING.extend("CC");
    public static final EntityRoleType MESSAGING_BCC = MESSAGING.extend("BCC");
    public static EntityRoleType PURCHASED = EntityRoleType.AFFECTS.extend("PURCHASED");

    @Test
    public void client_ip() {
        Event ev = event("action")
                .aspects(new ClientIPAddressAspect().clientIp("127.0.0.1"))
                .involves(role(ACTOR, entity(PERSON, "Petar")));

        Map expected = map(
                "type", "action",
                "involves", list(
                        map(
                                "entity_ref", "Person/Petar",
                                "role", "ACTOR"
                        )
                ),
                "aspects", map("client_ip", "127.0.0.1")
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void client_device() {
        Event ev = event("action")
                .aspects(new ClientDeviceAspect().clientDevice("iPhone"))
                .involves(role(ACTOR, entity(PERSON, "Petar")));

        Map expected = map(
                "type", "action",
                "involves", list(
                        map(
                                "entity_ref", "Person/Petar",
                                "role", "ACTOR"
                        )
                ),
                "aspects", map("client_device", "iPhone")
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void commerce_aspect() throws ParseException {
        EntityType POI = new EntityType("Poi");
        EntityType Serial = new EntityType("Serial");
        Event ev = event("action")
                .aspects(commerce(
                                item()
                                        .involves(role(PURCHASED, entity(POI, "12344542352345345")))
                                        .commissionFixed(1540.0)
                                        .commissionPercentage(150D)
                                        .discountPercentage(15D)
                                        .taxPercentage(1.0)
                                        .itemCount(2)
                                        .totalInStock(2.5)
                                        .totalForSale(3.5)
                                        .itemPrice(15400.0)
                                        .serialNumbers(entity(Serial, "1234"), entity(Serial, "4567"))
                                        .validFrom(DateHelpers.dateFormatter.parse("2015-11-24T17:00:00.000Z"), TimeZone.getTimeZone("UTC"))
                                        .validUntil(DateHelpers.dateFormatter.parse("2015-11-24T18:00:00.000Z"), TimeZone.getTimeZone("GMT+10"))
                                        .description("desc")
                                        .variant("variant")
                                        .priceCategory("A")
                                        .currency("ISK")
                                        .accountingKey("KEY")
                        )
                );
        Map expected = map(
                "type", "action",
                "aspects", map(
                        "items", list(
                                map(
                                        "involves", list(
                                                map(
                                                        "role", "AFFECTS:PURCHASED",
                                                        "entity_ref", "Poi/12344542352345345"
                                                )
                                        ),
                                        "item_count", 2,
                                        "item_price", 15400.0,
                                        "serial_numbers", list(map("entity_ref", "Serial/1234"), map("entity_ref", "Serial/4567")),
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
                                        "valid_until", "2015-11-25T04:00:00.000+10",
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
                                .type("car")
                                .categories("Europe", "Sweden", "family")
                                .variant("station wagon")
                                .make("Volvo")
                                .model("244GL")
                                .size("family")
                                .year(1976)
                );

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "classification", map(
                                "type", "car",
                                "categories", list("Europe", "Sweden", "family"),
                                "variant", "station wagon",
                                "model", "244GL",
                                "make", "Volvo",
                                "size", "family",
                                "year", 1976
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
                                .begins(DateHelpers.dateFormatter.parse("2015-11-24T17:00:00.000Z"), TimeZone.getTimeZone("GMT+1"))
                                .ends(DateHelpers.dateFormatter.parse("2015-11-24T20:00:00.000Z"), TimeZone.getTimeZone("GMT+2"))
                );

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "timed", map(
                                "begins", "2015-11-24T18:00:00.000+01",
                                "ends", "2015-11-24T22:00:00.000+02",
                                "type", "period"
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void pageview_aspect() throws ParseException {
        Map referrerProps = Factories.getMap();
        referrerProps.put("a", "b");
        referrerProps.put("c", 22);
        Event ev = event("action")
                .aspects(pageview("/path")
                                .pathProperties(Factories.getMap())
                                .keywords("a", "b", "c")
                                .method(RequestMethod.GET)
                                .referrer("http://localhost")
                                .referrerProperties(referrerProps)
                                .responseCode(200)
                                .size(100)
                                .protocol("HTTP")
                                .pageContent(rel().link(FEATURED, entity(PERSON, "Jane Doe")))
                );

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "pageview", map(
                                "path", "/path"
                                , "keywords", list("a", "b", "c")
                                , "referrer", "http://localhost"
                                , "referrer_properties", map("a", "b", "c", 22)
                                , "method", "GET"
                                , "response_code", 200
                                , "size", 100
                                , "protocol", "HTTP"
                                , "page_content", list(
                                        map("type", "FEATURED", "entity_ref", "Person/Jane Doe")
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

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "geo_location", map(
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

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "presentation", map(
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

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "dimensions", map(
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

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "metrics", map(
                                "key 1", 1.0
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void tags_aspect() throws MalformedURLException {
        Event ev = event("action")
                .aspects(
                        tags("a", "b", "c")
                );

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "tags", list("a", "b", "c")
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void locale_aspect() throws MalformedURLException {
        Event ev = event("action")
                .aspects(
                        locale()
                                .locale("en-GB")
                                .currency("USD")
                                .timezone(TimeZone.getTimeZone("GMT+01"))
                );

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "locale", map(
                                "locale", "en-GB",
                                "currency", "USD",
                                "timezone", "GMT+01:00"
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void inventory_aspect() throws MalformedURLException {
        Event ev = event("action")
                .aspects(
                        inventory()
                                .itemsForSale(10D)
                                .itemsInStock(100D)
                );

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "inventory", map(
                                "items_for_sale", 10D,
                                "items_in_stock", 100D
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void messaging_aspect() throws MalformedURLException {
        Event ev = event("action")
                .aspects(
                        messaging()
                                .involves(
                                        role(MESSAGING_FROM, entity(EMAIL, "one@all.com")),
                                        role(MESSAGING_TO, entity(EMAIL, "two@all.com")),
                                        role(MESSAGING_TO, entity(EMAIL, "three@all.com")),
                                        role(MESSAGING_CC, entity(EMAIL, "two@all.com")),
                                        role(MESSAGING_CC, entity(EMAIL, "three@all.com")),
                                        role(MESSAGING_BCC, entity(EMAIL, "two@all.com")),
                                        role(MESSAGING_BCC, entity(EMAIL, "three@all.com"))
                                )
                                .content("hi, how is it going?")
                                .subject("a question for you")
                                .group(true)
                                .properties(m().key("a").value(12))
                                .url("http://google.com")
                );

        Map expected = map(
                "type", "action",
                "aspects", map(
                        "messaging", map(
                                "involves", list(
                                        map(
                                                "role", "MESSAGING:FROM",
                                                "entity_ref", "Email/one@all.com"
                                        ),
                                        map(
                                                "role", "MESSAGING:TO",
                                                "entity_ref", "Email/two@all.com"
                                        ),
                                        map(
                                                "role", "MESSAGING:TO",
                                                "entity_ref", "Email/three@all.com"
                                        ),
                                        map(
                                                "role", "MESSAGING:CC",
                                                "entity_ref", "Email/two@all.com"
                                        ),
                                        map(
                                                "role", "MESSAGING:CC",
                                                "entity_ref", "Email/three@all.com"
                                        ),
                                        map(
                                                "role", "MESSAGING:BCC",
                                                "entity_ref", "Email/two@all.com"
                                        ),
                                        map(
                                                "role", "MESSAGING:BCC",
                                                "entity_ref", "Email/three@all.com"
                                        )
                                ),

                                "subject", "a question for you",
                                "content", "hi, how is it going?",
                                "group", true,
                                "url", "http://google.com",
                                "properties", map("a", 12)
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

}
