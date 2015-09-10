
import com.activitystream._
import com.activitystream.aspects.ClientDeviceAspect
import org.junit.runner.RunWith
import org.specs2.matcher.JsonMatchers
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner


@RunWith(classOf[JUnitRunner])
class EventTest extends Specification with JsonMatchers {

  "Should create an event with id" should {
    "ev is equal to" in {
      val event = new Event().action(new EventType("as.xcommerce.purchase.completed")).aspects(new ClientDeviceAspect().clientDevice("firefox")).toJson
      event must /("action" -> "as.xcommerce.purchase.completed")
      event must /("aspects") /("client_device" -> "firefox")
    }
  }

}
