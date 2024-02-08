package com.coderscampus.coursereportapiintegration;

import com.coderscampus.coursereportapiintegration.service.SlackBot;
import org.junit.jupiter.api.Test;

public class SlackBotTest {


    @Test
    public void slack_post_message_test () {
        SlackBot.postMessage("Test message 3");
    }
}
