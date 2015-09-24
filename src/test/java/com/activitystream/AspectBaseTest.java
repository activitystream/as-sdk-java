package com.activitystream;

import com.activitystream.aspects.AspectBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.activitystream.EventTestBase.obj;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AspectBaseTest {
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

        Map actual = new HashMap();
        new DeepAspect().addToObject(actual);

        Map expected = obj(
                "a", obj(
                    "b", obj(
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

        Map actual = new HashMap();
        new DeepAspect().addToObject(actual);

        Map expected = obj(
                "a", obj(
                    "b", obj(
                            "c", "1"
                        )
                ));
        assertThat(actual.entrySet(), equalTo(expected.entrySet()));

    }
}