package com.david.net;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {

    public static final String USER_API_URL = "https://jsonplaceholder.typicode.com/users/1";

    static void main() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(HttpRequest.newBuilder()
                            .uri(URI.create(USER_API_URL))
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("Response: " + response.body());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
