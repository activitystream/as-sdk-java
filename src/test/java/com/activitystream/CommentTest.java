package com.activitystream;

import org.junit.Test;

import java.util.Map;

import static com.activitystream.EventTestBase.list;
import static com.activitystream.EventTestBase.map;
import static com.activitystream.Predefined.COMMENTED_ON;
import static com.activitystream.Predefined.COMMENTS;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommentTest {
    @Test
    public void should_render_as_ref_entity_when_only_id_is_present() {
        Comment entity = comment("that car is totally worth it").involves(role(COMMENTS, entity("Person", "A")), role(COMMENTED_ON, entity("Car", "Volvo")));
        Map expected = map(
                "type", "as.api.comment",
                "comment", "that car is totally worth it",
                "involves", list(
                        map(
                                "role", "COMMENT:COMMENTS",
                                "entity_ref", "Person/A"
                        ),
                        map(
                                "role", "COMMENT:COMMENTED_ON",
                                "entity_ref", "Car/Volvo"
                        )
                )
        );

        Map actual = entity.toMap();
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }

}
