package com.activitystream;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LinkTypesTest {
    @Test
    public void should_allow_extending_predefined_links(){
        RoleType PARTY_A = RoleType.ACTOR.extend("PARTY_A");
        assertThat(PARTY_A.value(), is(equalTo("ACTOR:PARTY_A")));
    }
    @Test
    public void should_allow_deep_extending_predefined_links(){
        RoleType PARTY_A = RoleType.ACTOR.extend("PARTY_A").extend("PARTY_B");
        assertThat(PARTY_A.value(), is(equalTo("ACTOR:PARTY_A:PARTY_B")));
    }

}
