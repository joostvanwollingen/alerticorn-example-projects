import nl.vanwollingen.alerticorn.api.Message;
import nl.vanwollingen.alerticorn.slack.Slack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Message(title = "The default message")
public class ExampleTest extends AlerticornTest {

    private final String mySlackHook = "https://hooks.slack.com/services/<make>/<sure>/<to_replace>";

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
}
