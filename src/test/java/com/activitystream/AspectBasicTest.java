package com.activitystream;

import com.activitystream.aspects.AspectBase;
import com.activitystream.underware.Factories;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.activitystream.EventTestBase.map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AspectBasicTest {
    Set<String> processed = new HashSet<>();
    @Test
    public void should_create_tree_of_hashes() {
        class DeepAspect extends AspectBase {
            public DeepAspect() {
                aspectPropertyMap.put("a.b.c", new AspectProperty(IsRequired.False));
                aspectPropertyMap.put("a.b.d", new AspectProperty(IsRequired.False));

                aspectPropertyMap.get("a.b.c").value = "1";
                aspectPropertyMap.get("a.b.d").value = "2";
            }
        }

        Map actual = Factories.getMap();
        new DeepAspect().addToObject(actual, processed);

        Map expected = map(
                "a", map(
                        "b", map(
                                "c", "1",
                                "d", "2"
                        )
                ));
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }

    @Test
    public void should_skip_items_that_have_not_been_set() {
        class DeepAspect extends AspectBase {
            public DeepAspect() {
                aspectPropertyMap.put("a.b.c", new AspectProperty(IsRequired.False));
                aspectPropertyMap.put("a.b.d", new AspectProperty(IsRequired.False));

                aspectPropertyMap.get("a.b.c").value = "1";
            }
        }

        Map actual = Factories.getMap();
        new DeepAspect().addToObject(actual, processed);

        Map expected = map(
                "a", map(
                        "b", map(
                                "c", "1"
                        )
                ));
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }
}
