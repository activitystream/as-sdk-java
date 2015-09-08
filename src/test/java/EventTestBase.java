import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EventTestBase {
    protected String json(String json) {
        try {
            Object parsed = null;
            parsed = new JSONParser().parse(json);
            return JSONValue.toJSONString(parsed);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
