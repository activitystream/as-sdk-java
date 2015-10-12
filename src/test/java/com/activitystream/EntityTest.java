package com.activitystream;

import com.activitystream.underware.Factories;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.activitystream.EventTestBase.list;
import static com.activitystream.EventTestBase.map;
import static com.activitystream.Predefined.*;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntityTest {
    Set<String> processed = new HashSet<>();
    @Test
    public void should_render_as_ref_entity_when_only_id_is_present() {
        Entity entity = entity("Person", "id");
        Map expected = map(
                "entity_ref", "Person/id"
        );

        Map actual = entity.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }

    @Test
    public void should_render_as_embedded_entity_when_any_propery_besides_id_is_present() {
        Entity entity = entity("Person", "id").properties(m().key("a").value("b"));
        Map expected = map(
                "entity", map(
                        "entity_ref", "Person/id",
                        "properties", map("a", "b")
                )
        );

        Map actual = entity.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }

    @Test
    public void should_render_embedded_entities_only_once_and_the_rest_as_references() {
        Entity entity = entity("Person", "Petar").properties(m().key("a").value("b"));
        Event ev = event("test")
                .involves(role(ACTOR, entity), role(INVOLVES, entity))
                .aspects(commerce(
                        item().involves(role(ACTOR, entity))
                ));

        Map expected = map(
                "type", "test",
                "involves", list(
                        map(
                                "role", "ACTOR",
                                "entity", map(
                                        "entity_ref", "Person/Petar",
                                        "properties", map("a", "b")
                                )
                        ),
                        map(
                                "role", "INVOLVES",
                                "entity_ref", "Person/Petar"
                        )
                ),
                "aspects", map(
                        "items", list(
                                map(
                                        "involves", list(
                                                map(
                                                        "role", "ACTOR",
                                                        "entity_ref", "Person/Petar"
                                                )
                                        )
                                )
                        )
                )
        );

        assertThat(ev.toMap().entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_render_embedded_entities_only_once_but_allow_rerendering_of_two_instances_of_the_same_entity() {
        Entity entity1 = entity("Person", "Petar").properties(m().key("a").value("b"));
        Entity entity2 = entity("Person", "Petar").aspects(clientIp("1.1.1.1"));
        Event ev = event("test")
                .involves(role(ACTOR, entity1), role(INVOLVES, entity2));

        Map expected = map(
                "type", "test",
                "involves", list(
                        map(
                                "role", "ACTOR",
                                "entity", map(
                                        "entity_ref", "Person/Petar",
                                        "properties", map("a", "b")
                                )
                        ),
                        map(
                                "role", "INVOLVES",
                                "entity", map(
                                        "entity_ref", "Person/Petar",
                                        "aspects", map(
                                                "client_ip", "1.1.1.1"
                                        )
                                )
                        )
                )
        );

        assertThat(ev.toMap().entrySet(), equalTo(expected.entrySet()));
    }
}
