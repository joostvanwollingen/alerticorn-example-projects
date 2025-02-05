import nl.vanwollingen.alerticorn.api.AlerticornMessage;
import nl.vanwollingen.alerticorn.api.Message;
import nl.vanwollingen.alerticorn.api.RunWith;
import nl.vanwollingen.alerticorn.slack.Slack;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Message(title = "The default message")
public class ExampleTest extends AlerticornTest {

    private final String mySlackHook = "https://hooks.slack.com/services/<remembe>/<to>/<replace>";

    @Slack
    @Test
    @Message.Channel(mySlackHook)
    public void my_test() {
        assertEquals(false, true);
    }

    @Slack
    @Test
    @Message.Channel(mySlackHook)
    @Message(title = "But you can override it")
    public void my_overriden_message_test() {
        assertEquals(false, true);
    }

    @Test
    public void some_method() {
        String platform = "slack";
        String destination = mySlackHook;
        String title = "Message Alert";

        BiFunction<String, String, AlerticornMessage> buildMessage = (context, result) ->
                new AlerticornMessage("Context: " + context + ", Result: " + result,null, null, null, null);

        Supplier<String> block = () -> "Test message result";

        String result = RunWith.INSTANCE.message(platform, destination, title, buildMessage::apply, block::get);
        System.out.println("Result: " + result);
    }
}
