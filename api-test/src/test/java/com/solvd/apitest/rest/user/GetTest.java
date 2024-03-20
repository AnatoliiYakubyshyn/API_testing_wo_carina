package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.solvd.apitest.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.apitest.R;

public class GetTest {

    @Test
    public void testPublicUser() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(R.getRestUrl())).GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testSpecificUser() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(R.getRestUrl() + "6777744")).GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            Assert.assertEquals(Json.parseString(response.body()),
                    Json.parseString("\"id\":6777744,\"name\":\"Divya Jha\",\"email\":\"divya_jha@corkery.example\",\"gender\":\"male\",\"status\":\"active\"}"));
        } else {
            Assert.assertEquals(response.statusCode(), 404);
        }
    }

    @Test
    public void testUnavailableUser() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(R.getRestUrl() + "67777440")).GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(response.statusCode(), 404);
    }
}
