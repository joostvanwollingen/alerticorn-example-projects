package org.example

import nl.vanwollingen.alerticorn.api.*
import nl.vanwollingen.alerticorn.discord.Discord
import nl.vanwollingen.alerticorn.junit.MessageExtension
import nl.vanwollingen.alerticorn.slack.Slack
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

/*

This is a simple example test, to show how you could use Alerticorn.
You'll have to provide valid webhooks on the lines below for messages to come in.
If you don't use Discord or Slack, simply replace @Slack with @Discord or vice-versa.
 */

//Remember to replace these with your own hooks!
const val mySlackHook = "https://hooks.slack.com/services/<your>/<slack>/<hook>"
const val myDiscordHook = "https://discord.com/api/webhooks/<your>/<discord_hook>"

@ExtendWith(MessageExtension::class)
class Examples {

    @Test
    @Message(title = "The test will not alert unless it passes")
    @Message.Events([Event.PASS])
    @Message.Channel(mySlackHook)
    @Slack
    fun alertOnPass() {
        assertEquals(true, false)
    }

    @Test
    @Slack
    @Message(title= "Comment this line to stop messaging")
    @Message.Channel(mySlackHook)
    fun wont_alert_without_a_title() {
        throw Error("oh sh*t!", Error("it hit the fan"))
    }
//
    @Test
    @Discord
    @Message(title = "Template test")
    @Message.Template("myTemplate")
    @Message.Channel(myDiscordHook)
    fun this_uses_a_template_to_build_the_message() {
        throw Error("faaail")
    }

    @Test
    fun you_can_also_use_run_with_instead_of_annotations() {

        RunWith.message(
            platform = "slack",
            destination = mySlackHook,
            title = "Hello",
            buildMessage = { message, value -> AlerticornMessage(message, "the value was $value") },
            block = { "World!" })

        assertThrows<Error>() {
            RunWith.messageOnException(
                platform = "slack",
                destination = mySlackHook,
                title = "An urgent message from the chief engineer",
                messageBuilder = { message, value -> AlerticornMessage(message, "the error was $value") },
                block = {
                    throw Error("the flux capacitor overloaded")
                }
            )
        }
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup(): Unit {
            MessageProvider.store("myTemplate") { title, throwable ->
                AlerticornMessage(
                    title = "The title was $title",
                    body = "This went wrong: ${throwable?.message}",
                    details = mapOf("Template" to "myTemplate", "Demo" to "Stration"),
                    links = mapOf("google" to "https://www.google.com"),
                    throwable,
                )
            }
        }

        @JvmStatic
        @BeforeAll
        fun requireHooks(): Unit {
            require(!mySlackHook.endsWith("<hook>")) { "Remember to replace mySlackHook with one of your own"}
            require(!myDiscordHook.endsWith("_hook>")) { "Remember to replace myDiscordHook with one of your own"}
        }
    }
}