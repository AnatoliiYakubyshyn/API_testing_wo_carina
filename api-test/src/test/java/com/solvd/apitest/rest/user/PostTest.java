package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import com.solvd.apitest.TemplateJsonService;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.apitest.R;
import com.solvd.apitest.RequestService;
import com.solvd.apitest.enums.HTTP_METHOD;

public class PostTest {

    private final String postTemplateFile = "src/test/resources/post.json";

    @Test
    public void testPostUser() throws IOException, InterruptedException {
        String body = TemplateJsonService.readAndFillTemplate(postTemplateFile,"Marty McFly",
                "marty@gmail.com","male","active");
        HttpResponse<String> response = RequestService.changeRequest(R.getRestUrl(), body, HTTP_METHOD.POST,
                true);
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 201, String.valueOf(
                response.statusCode()));
        response = RequestService.getRequestWithNoBody(R.getRestUrl(), true);
        JsonNode jsonNode = Json.parseString(response.body());
        boolean f = false;
        String id = "";
        for (int i = 0; i < jsonNode.size(); i++) {
            if (jsonNode.get(i).get("name").toString().equals("\"Marty McFly\"")) {
                f = true;
                id = jsonNode.get(i).get("id").toString();
                break;
            }
        }
        Assert.assertTrue(f, "No such person. Data is not saved");
        response = RequestService.delete(R.getRestUrl() + id, true);
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204, String.valueOf(
                response.statusCode()));
    }

    @Test
    public void testEmptyUser() throws IOException, InterruptedException {
        HttpResponse<String> response = RequestService.changeRequest(R.getRestUrl(), "", HTTP_METHOD.POST,
                true);
        Assert.assertEquals(response.statusCode(), 422);
    }
}
