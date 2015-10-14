package com.activitystream;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EntityRelationTypesTest {
    @Test
    public void should_allow_extending_predefined_links(){
        EntityRelationType PARTY_A = Predefined.AKA.extend("NICKNAME");
        assertThat(PARTY_A.value(), is(equalTo("AKA:NICKNAME")));
    }
    @Test
    public void should_allow_deep_extending_predefined_links(){
        EntityRelationType PARTY_A = Predefined.AKA.extend("NICKNAME").extend("ABBREVIATION");
        assertThat(PARTY_A.value(), is(equalTo("AKA:NICKNAME:ABBREVIATION")));
    }

}
