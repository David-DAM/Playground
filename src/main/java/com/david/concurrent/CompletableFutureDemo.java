package com.david.concurrent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    static void main(String[] args) {

        CompletableFuture<Void> task1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("Async task 1");
                    sleep();
                });

        CompletableFuture<Void> task2 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("Async task 2");
                    sleep();
                });

        CompletableFuture.allOf(task1, task2).join();
        System.out.println("All tasks finished");

        CompletableFuture<Void> getUser = CompletableFuture.supplyAsync(() -> 1)
                .thenApply(Object::toString)
                .thenCompose(x -> CompletableFuture.supplyAsync(() -> fetchUser(x)))
                .thenAccept(System.out::println);

        getUser.join();
    }

    private static String fetchUser(String x) {
        try (var client = HttpClient.newHttpClient()) {
            var response = client.send(HttpRequest.newBuilder()
                            .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + x))
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}

