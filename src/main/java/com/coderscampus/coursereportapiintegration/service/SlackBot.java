package com.coderscampus.coursereportapiintegration.service;

import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SlackBot {

    // Replace with your bot token and channel ID
    private static final String TOKEN = "xoxb-6630589301536-6630649693296-H3XlTc7BgCIX0GJjg4dC1JxJ";
    private static final String CHANNEL_ID = "example-app";
    private static final String SLACK_API_URL = "https://slack.com/api/chat.postMessage";

    public static void postMessage(String message) {
        try {
            URL url = new URL(SLACK_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + TOKEN);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

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
