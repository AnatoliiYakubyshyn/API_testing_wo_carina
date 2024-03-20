package com.solvd.apitest.graphql.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.apitest.R;

public class RetrievingUserTest {

    @Test
    public void testTenUsers() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String body = "{ \"query\": \"{users(first: 10) {edges {node {id}}}}\"\n" +
                "}";
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(R.getGraphQLUrl())).
                headers("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString
                        (body, StandardCharsets.UTF_8)).build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = Json.parseString(response.body());
        Assert.assertEquals(jsonNode.get("data").get("users").get("edges").size(),10);
    }
}
