package com.activitystream;

import com.activitystream.aspects.AddressAspect;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import static com.activitystream.EntityRoleType.ACTOR;
import static com.activitystream.EntityRoleType.INVOLVES;
import static com.activitystream.Predefined.AKA;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.fail;

public class EventTest extends EventTestBase {
    public static final EntityType BUILDING = new EntityType("Building");

    @Test
    public void should_create_an_event_with_id() {
        Event ev = event("as.xcommerce.purchase.completed");
        Map expected = map(
                "type", "as.xcommerce.purchase.completed"
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_create_event_with_involved_actor_by_ref() {
        Event ev = event("action")
                .involves(role(ACTOR,entity(EMPLOYEE, "Petar")).properties(m().key("a").value("b")));
        Map expected = map(
                "type", "action",
                "involves", list(
                        map(
                                "entity_ref", "Employee/Petar",
                                "role", "ACTOR",
                                "properties", map(
                                        "a", "b"
                                )
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_allow_for_null_as_role_for_those_cases_that_we_do_not_have_the_role_available() {
        Event ev = event("action")
                .involves(role(ACTOR, entity(EMPLOYEE, "Petar")), null, role(INVOLVES, entity(EMPLOYEE, "Stefan")));
        Map expected = map(
                "type", "action",
                "involves", list(
                        map(
                                "entity_ref", "Employee/Petar",
                                "role", "ACTOR"
                        ),
                        map(
                                "entity_ref", "Employee/Stefan",
                                "role", "INVOLVES"
                        )
                )
        );
        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }


    @Test
    public void should_create_event_with_involved_embedded_actor() throws ParseException {
        Event ev = event("action")
                .involves(role(ACTOR, entity(PERSON, "Petar")
                                .properties(m().key("favourite_programming_language").value("javascript"))
                                .aspects(
                                        classification().type("type")
                                )
                                .relations(
                                        rel().link(AKA, entity(EMAIL, "pshomov@gmail.com")).validFrom(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2014-12-01T10:00:00"), TimeZone.getTimeZone("GMT+08")),
                                        rel().link(AKA, entity(TWITTER, "pshomov")),
                                        rel().link(AKA, entity(BUILDING, "Laugavegur 26"))
                                )
                ));
        Map expected = map(
                "type", "action",
                "involves", list(
                        map(
                                "role", "ACTOR",
                                "entity", map(
                                        "entity_ref", "Person/Petar",
                                        "properties", map("favourite_programming_language", "javascript"),
                                        "aspects", map(
                                                "classification", map(
                                                        "type", "type"
                                                )

                                        ),
                                        "relations", list(
                                                map(
                                                        "type", "AKA",
                                                        "entity_ref", "Email/pshomov@gmail.com",
                                                        "valid_from", "2014-12-01T18:00:00.000+08"
                                                ),
                                                map(
                                                        "type", "AKA",
                                                        "entity_ref", "Twitter/pshomov"
                                                ),
                                                map(
                                                        "type", "AKA",
                                                        "entity_ref", "Building/Laugavegur 26"
                                                )
                                        )
                                )
                        )
                )
        );

        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_create_event_with_all_attributes() throws ParseException {
        Event ev = event("action")
                .origin("browserX")
                .description("some text")
                .properties(m().key("favourite_programming_language").value("javascript"))
                .occurred(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2014-12-01T10:00:00"), TimeZone.getTimeZone("GMT+01"));

        Map expected = map(
                "type", "action",
                "description", "some text",
                "properties", map("favourite_programming_language", "javascript"),
                "occurred_at", "2014-12-01T11:00:00.000+01",
                "origin", "browserX"
        );

        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_create_event_with_aspects() {
        Event ev = event("action")
                .aspects(new AddressAspect().city("Reykjavík").countryCode("IS").secondAddressLine("").streetAndNumber("Laugavegur 26").zipCode("2400"))
                .involves(role(ACTOR, entity(PERSON, "Petar")));

        Map expected = map(
                "type", "action",
                "involves", list(
                        map(
                                "role", "ACTOR",
                                "entity_ref", "Person/Petar"
                        )
                ),
                "aspects", map(
                        "address", map(
                                "address", "Laugavegur 26",
                                "city", "Reykjavík",
                                "zip_code", "2400",
                                "address2", "",
                                "country_code", "IS"
                        )
                )

        );

        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_not_allow_relationship_with_no_linked_entity() {
        Event ev = event("action")
                .involves(role(ACTOR, entity(PERSON, "Petar")
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

    @Test
    public void should_allow_specifying_an_external_id_on_a_role() {
        Event ev = event("action")
                .involves(role(ACTOR, entity(PERSON, "Petar")
                                .relations(
                                        rel().link(AKA, entity(PERSON, "Petar")).externalId("Irene")
                                )
                ));
        Map expected = map(
                "type", "action",
                "involves", list(
                        map(
                                "role", "ACTOR",
                                "entity", map(
                                        "entity_ref", "Person/Petar",
                                        "relations", list(
                                                map(
                                                        "type", "AKA",
                                                        "entity_ref", "Person/Petar",
                                                        "external_id", "Irene"
                                                )
                                        )
                                )
                        )
                )
        );

        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }


    @Test
    public void should_allow_overriding_of_aspects() {
        Event ev = event("action")
                .aspects(clientIp("1"), clientIp("2"));
        Map expected = map(
                "type", "action",
                "aspects", map(
                        "client_ip", "2"
                )

        );

        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_allow_adding_of_aspects_to_events() {
        Event ev = event("action")
                .aspects(clientIp("1"), clientIp("2"));

        ev.aspects(clientDevice("browser"));
        Map expected = map(
                "type", "action",
                "aspects", map(
                        "client_ip", "2",
                        "client_device", "browser"
                )

        );

        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_allow_adding_of_involved_roles_to_events() {
        Event ev = event("action")
                .involves(role(ACTOR, entity(PERSON, "Petar")));

        ev.involves(role(ACTOR, entity(PERSON, "NotPetar")));
        Map expected = map(
                "type", "action",
                "involves", list(
                        map(
                                "role", "ACTOR",
                                "entity_ref", "Person/Petar"
                        ),
                        map(
                                "role", "ACTOR",
                                "entity_ref", "Person/NotPetar"
                        )
                )
        );

        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_trim_down_empty_lists_and_maps_when_serializing() {
        Event ev = event("action")
                .involves(null)
                .aspects(null, null)
                .properties(m());

        Map expected = map(
                "type", "action"
        );

        Map actual = ev.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));
    }

}
