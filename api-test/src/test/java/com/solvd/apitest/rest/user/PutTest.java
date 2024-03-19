package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.apitest.R;

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

    @Test
    public void testSuccessfulUpdate() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url)).header("Authorization", "Bearer "
                + R.readToken()).headers("Content-Type", "application/json").GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(response.statusCode(), 200);
        JsonNode jsonNode = Json.parseString(response.body()).get(0);
        System.out.println(jsonNode.toString());
        String query = "{\"status\":" + (
                jsonNode.get("status").toString().equals("\"active\"") ?
                        "\"inactive\"" : "\"active\"") + "}";
        System.out.println(query);
        httpRequest = HttpRequest.newBuilder(URI.create(url + jsonNode.get("id"))).header("Authorization", "Bearer "
                        + R.readToken()).headers("Content-Type", "application/json").PUT(
                        HttpRequest.BodyPublishers.ofString(
                                query, StandardCharsets.UTF_8)
                ).
                build();
        response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertTrue(response.statusCode() >= 200 && response.statusCode() <= 299,
                "response is not 200 - 299 " + response.statusCode());
    }
}
