package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PutTest {

    private final String url = "https://gorest.co.in/public/v2/users/";

    @Test
    public void testPutWithoutAuth() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String query = "{\n" +
                "\n" +
                "\"name\":\"Chandak Gandhi\",\n" +
                "\"email\":\"chandak_gandhi@durgan.test\",\n" +
                "\"gender\":\"female\",\n" +
                "\"status\":\"active\"\n" +
                "}";
        HttpRequest.BodyPublisher bodyPublisher
                = HttpRequest.BodyPublishers.ofString(query, StandardCharsets.UTF_8);
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url + "6777736")).PUT(bodyPublisher).build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertTrue(response.statusCode() == 401 || response.statusCode() == 404);
    }
}
