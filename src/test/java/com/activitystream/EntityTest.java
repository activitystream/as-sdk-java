package com.activitystream;

import org.junit.Test;

import java.util.Map;
import static com.activitystream.EventTestBase.list;
import static com.activitystream.EventTestBase.map;
import static com.activitystream.Predefined.ACTOR;
import static com.activitystream.Predefined.ASSOCIATED_WITH;
import static com.activitystream.Predefined.INVOLVES;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntityTest {
    @Test
    public void should_render_as_ref_entity_when_only_id_is_present() {
        Entity entity = entity("Person", "id");
        Map expected = map(
                "type", "as.api.entity",
                "entity_ref", "Person/id"
        );

        Map actual = entity.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }

    @Test
    public void should_render_as_embedded_entity_when_any_propery_besides_id_is_present() {
        Entity entity = entity("Person", "id").properties(m().key("a").value("b"));
        Map expected = map(
                "type", "as.api.entity",
                "entity_ref", "Person/id",
                "properties", map("a", "b")
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
    @Test
    public void should_allow_for_null_relation_to_facilitate_the_builder_pattern() {
        Entity entity1 = entity("Person", "Petar");
        Entity entity2 = entity("Person", "Stefan");

        Entity group = entity("Group", "ASCore").relations(rel().link(ASSOCIATED_WITH, entity1), null, rel().link(ASSOCIATED_WITH, entity2));

        Map expected = map(
                "type", "as.api.entity",
                "entity_ref", "Group/ASCore",
                "relations", list(
                        map(
                                "type", "ASSOCIATED_WITH",
                                "entity_ref", "Person/Petar"
                        ),
                        map(
                                "type", "ASSOCIATED_WITH",
                                "entity_ref", "Person/Stefan"
                        )
                )
        );

        assertThat(group.toMap().entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_allow_for_some_null_aspects_to_facilitate_the_builder_pattern() {
        Entity group = entity("Group", "ASCore").aspects(clientIp("2323"), null);

        Map expected = map(
                "type", "as.api.entity",
                "entity_ref", "Group/ASCore",
                "aspects", map(
                        "client_ip", "2323"
                )
        );

        assertThat(group.toMap().entrySet(), equalTo(expected.entrySet()));
    }

    @Test
    public void should_allow_for_all_null_aspects_to_facilitate_the_builder_pattern() {
        Entity group = entity("Group", "ASCore").aspects(null, null);

        Map expected = map(
                "type", "as.api.entity",
                "entity_ref", "Group/ASCore"
        );

        assertThat(group.toMap().entrySet(), equalTo(expected.entrySet()));
    }

}
