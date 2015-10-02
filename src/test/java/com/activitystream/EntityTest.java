package com.activitystream;

import com.activitystream.underware.Factories;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.activitystream.EventTestBase.arr;
import static com.activitystream.EventTestBase.obj;
import static com.activitystream.Predefined.*;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntityTest {
    Set<String> processed = new HashSet<>();
    @Test
    public void should_render_as_ref_entity_when_only_id_is_present() {
        Entity entity = entity("Person", "id");
        Map expected = obj(
                "entity_ref", "Person/id"
        );

        Map actual = Factories.getMap();
        entity.addToObject(actual, processed);
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }

    @Test
    public void should_render_as_embedded_entity_when_any_propery_besides_id_is_present() {
        Entity entity = entity("Person", "id").properties(m().key("a").value("b"));
        Map expected = obj(
                "entity", obj(
                        "entity_ref", "Person/id",
                        "properties", obj("a", "b")
                )
        );

        Map actual = Factories.getMap();
        entity.addToObject(actual, processed);
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }

    @Test
    public void should_render_embedded_entities_only_once_and_the_rest_as_references() {
        Entity entity = entity("Person", "Petar").properties(m().key("a").value("b"));
        Event ev = event("test")
                .involves(role(ACTOR, entity), role(INVOLVES, entity))
                .aspects(eCommerce(
                        item().involves(role(ACTOR, entity))
                ));

        Map expected = obj(
                "type", "test",
                "involves", arr(
                        obj(
                                "role", "ACTOR",
                                "entity", obj(
                                        "entity_ref", "Person/Petar",
                                        "properties", obj("a", "b")
                                )
                        ),
                        obj(
                                "role", "INVOLVES",
                                "entity_ref", "Person/Petar"
                        )
                ),
                "aspects", obj(
                        "items", arr(
                                obj(
                                        "involves", arr(
                                                obj(
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

        Map expected = obj(
                "type", "test",
                "involves", arr(
                        obj(
                                "role", "ACTOR",
                                "entity", obj(
                                        "entity_ref", "Person/Petar",
                                        "properties", obj("a", "b")
                                )
                        ),
                        obj(
                                "role", "INVOLVES",
                                "entity", obj(
                                        "entity_ref", "Person/Petar",
                                        "aspects", obj(
                                            "client_ip", "1.1.1.1"
                                        )
                                )
                        )
                )
        );

        assertThat(ev.toMap().entrySet(), equalTo(expected.entrySet()));
    }
}
