package com.solvd.apitest.graphql.user;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import com.solvd.apitest.RequestService;
import com.solvd.apitest.enums.HTTP_METHOD;
import com.solvd.apitest.R;

public class RetrievingUserTest {

    @Test
    public void testTenUsers() throws IOException, InterruptedException {
        String body = "{ \"query\": \"{users(first: 10) {edges {node {id}}}}\"\n" +
                "}";
        HttpResponse<String> response = RequestService.changeRequest(R.getGraphQLUrl(),
                body, HTTP_METHOD.POST,true);
        JsonNode jsonNode = Json.parseString(response.body());
        Assert.assertEquals(jsonNode.get("data").get("users").get("edges").size(), 10);
    }
}
