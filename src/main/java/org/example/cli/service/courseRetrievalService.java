package org.example.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class courseRetrievalService {
    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    public String findCourses(String author) {
        HttpClient Client = HttpClient.newBuilder().build();

        URI uri = URI.create(PS_URI.formatted(author));

        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

        HttpResponse response;

        {
            try {
                response = Client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return response.body().toString();
    }





}
