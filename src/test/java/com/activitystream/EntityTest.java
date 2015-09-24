package com.activitystream;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.activitystream.EventTestBase.obj;
import static com.activitystream.Sugar.entity;
import static com.activitystream.Sugar.m;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntityTest {
    @Test
    public void should_render_as_ref_entity_when_only_id_is_present() {
        Entity entity = entity("Person", "id");
        Map expected = obj(
                "entity_ref", "Person/id"
        );

        Map actual = new HashMap();
        entity.addToObject(actual);
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

        Map actual = new HashMap();
        entity.addToObject(actual);
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }
}
