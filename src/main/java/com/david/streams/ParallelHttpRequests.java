package com.david.streams;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class ParallelHttpRequests {

    static void main(String[] args) {

        try (HttpClient client = HttpClient.newHttpClient()) {

            List<String> ids = List.of("1", "2", "3", "4", "5");

            ids.parallelStream()
                    .map(id -> sendRequest(client, id))
                    .forEach(System.out::println);
        }
    }

    private static String sendRequest(HttpClient client, String id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/" + id))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
