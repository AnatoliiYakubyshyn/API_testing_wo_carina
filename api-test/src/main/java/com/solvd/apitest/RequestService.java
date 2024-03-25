package com.solvd.apitest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.solvd.apitest.enums.HTTP_METHOD;

public class RequestService {

    public static HttpResponse<String> getRequestWithNoBody(String url, boolean withAuth) throws IOException,
            InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = null;
        if (withAuth) {
            httpRequest = HttpRequest.newBuilder(URI.create(url)).GET().header("Authorization", "Bearer "
                    + R.readToken()).headers("Content-Type", "application/json").build();
        } else {
            httpRequest = HttpRequest.newBuilder(URI.create(url)).GET().build();
        }

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> changeRequest(String url, String body, HTTP_METHOD httpChangeMethod, boolean withAuth) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = null;
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url));
        if (withAuth) {
            builder.header("Authorization", "Bearer "
                    + R.readToken());
        }
        builder.headers("Content-Type", "application/json");
        switch (httpChangeMethod) {
            case PUT:
                httpRequest = builder.PUT(
                        HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();
                break;
            case POST:
                httpRequest = builder.POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8)).build();
        }
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> delete(String url, boolean withAuth) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = null;
        if (withAuth) {
            httpRequest = HttpRequest.newBuilder(URI.create(url)).DELETE().header("Authorization", "Bearer "
                    + R.readToken()).build();
        } else {
            httpRequest = HttpRequest.newBuilder(URI.create(url)).DELETE().build();
        }

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
