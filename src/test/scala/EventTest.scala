import com.activitystream.aspects.AddressAspect;
import com.activitystream.EntityType;
import com.activitystream.Event;
import com.activitystream.EventType;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static com.activitystream.Predefined.*;
import static com.activitystream.Sugar.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.fail;

import org.specs2.mutable.Specification
import org.specs2.matcher.JsonMatchers



class EventTest extends Specification with JsonMatchers {


"Should create an event with id" should {
    "ev is equal to" in {
        val event = ev.toJson
        event must /("event" -> "as.xcommerce.purchase.completed")
    }
}

....

    public static final EntityType BUILDING = new EntityType("Building");

    @Test
    public void should_create_an_event_with_id() {
        Event ev = new Event().type(new EventType("as.xcommerce.purchase.completed"));
        assertThat(ev.toJson(), equalTo(json("{ \"event\" : \"as.xcommerce.purchase.completed\" }")));
    }


}
