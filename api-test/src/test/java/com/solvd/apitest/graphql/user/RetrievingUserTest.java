package com.solvd.apitest.graphql.user;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import com.solvd.apitest.RequestService;
import com.solvd.apitest.TemplateJsonService;
import com.solvd.apitest.enums.HTTP_METHOD;
import com.solvd.apitest.R;

public class RetrievingUserTest {

    private final String getUserTemplatePath = "src/test/resources/graphql/getUsers.json";

    private final String getTemplatePath = "src/test/resources/graphql/getGeneral.json";

    @Test
    public void testTenUsers() throws IOException, InterruptedException {
        String body = TemplateJsonService.readAndFillTemplate(getUserTemplatePath,
                10);
        HttpResponse<String> response = RequestService.changeRequest(R.getGraphQLUrl(),
                body, HTTP_METHOD.POST, true);
        JsonNode jsonNode = Json.parseString(response.body());
        Assert.assertEquals(jsonNode.get("data").get("users").get("edges").size(), 10);
    }
    @Test
    public void testIncorrectUsers() throws IOException, InterruptedException {
        String body = TemplateJsonService.readAndFillTemplate(getUserTemplatePath,
                -10);
        HttpResponse<String> response = RequestService.changeRequest(R.getGraphQLUrl(),
                body, HTTP_METHOD.POST, true);
        JsonNode jsonNode = Json.parseString(response.body());
        Assert.assertEquals(jsonNode.get("data").get("users").get("edges").size(), 0);
    }

    @Test
    public void testPosts() throws IOException, InterruptedException {
        String body = TemplateJsonService.readAndFillTemplate(getTemplatePath,
                "posts",2);
        HttpResponse<String> response = RequestService.changeRequest(R.getGraphQLUrl(),
                body, HTTP_METHOD.POST, true);
        JsonNode jsonNode = Json.parseString(response.body());
        Assert.assertEquals(jsonNode.get("data").get("posts").get("edges").size(), 2);
    }

    @Test
    public void testTodos() throws IOException, InterruptedException {
        String body = TemplateJsonService.readAndFillTemplate(getTemplatePath,
                "todos",10);
        HttpResponse<String> response = RequestService.changeRequest(R.getGraphQLUrl(),
                body, HTTP_METHOD.POST, true);
        JsonNode jsonNode = Json.parseString(response.body());
        Assert.assertEquals(jsonNode.get("data").get("todos").get("edges").size(), 10);
    }
}
