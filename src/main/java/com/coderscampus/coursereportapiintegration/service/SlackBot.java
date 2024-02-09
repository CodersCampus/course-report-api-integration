package com.coderscampus.coursereportapiintegration.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class SlackBot {
    @Value("${slack.bot.token}")
    private final String TOKEN = null;
    @Value("${slack.channelId}")
    private final String CHANNEL_ID = null;
    private final String SLACK_API_URL = "https://slack.com/api/chat.postMessage";

    public void postMessage(String message) {
        try {
            URL url = new URL(SLACK_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + TOKEN);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            System.out.println("About to post this messsage to slack: " + message);
            String jsonPayload = "{\"channel\":\"" + CHANNEL_ID + "\",\"text\":\"" + message + "\"}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonPayload.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Handle the connection response...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
