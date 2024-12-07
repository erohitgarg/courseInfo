package org.example.cli.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class courseRetrievalService {
    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public List<PluralsightCourses> findCourses(String author) {
        HttpClient Client = HttpClient.newBuilder().build();

        URI uri = URI.create(PS_URI.formatted(author));

        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

        HttpResponse<String> response;

        {
            try {
                response = Client.send(request, HttpResponse.BodyHandlers.ofString());
                return switch(response.statusCode()) {
                    case 200 -> {
                        JavaType stringToList = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, PluralsightCourses.class);
                        yield OBJECT_MAPPER.readValue(response.body(), stringToList);
                    }
                    case 404 -> List.of();
                    default -> {
                        String err = "Unknown Response Code" + response.statusCode();
                        throw new RuntimeException(err);
                    }
                };

            } catch (IOException | InterruptedException e) {

                throw new RuntimeException("Could not connect to the API: {}", e);
            }
        }
    }





}
