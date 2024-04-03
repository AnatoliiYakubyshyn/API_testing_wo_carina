package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import com.solvd.apitest.TemplateJsonService;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import com.solvd.apitest.RequestService;
import com.solvd.apitest.enums.HTTP_METHOD;

public class PutTest {

    private final String url = "https://gorest.co.in/public/v2/users/";

    private final String putTemplateFile = "src/test/resources/put.json";

    @Test
    public void testPutWithoutAuth() throws IOException, InterruptedException {
        String postTemplateFile = "src/test/resources/post.json";
        String query = TemplateJsonService.readAndFillTemplate(postTemplateFile,"Chandak Gandhi",
                "chandak_gandhi@durgan.test","chandak_gandhi@durgan.test","female","active");
        HttpResponse<String> response = RequestService.changeRequest(url + "6777736", query, HTTP_METHOD.PUT,
                false);
        Assert.assertTrue(response.statusCode() == 401 || response.statusCode() == 404,"Actual result: " +response.statusCode());
    }

    @Test
    public void testSuccessfulUpdate() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = RequestService.getRequestWithNoBody(url, true);
        Assert.assertEquals(response.statusCode(), 200);
        JsonNode jsonNode = Json.parseString(response.body()).get(0);
        String query = TemplateJsonService.readAndFillTemplate(putTemplateFile,
                jsonNode.get("status").toString().equals("\"active\"") ?
                        "inactive" : "active" );
        response = RequestService.changeRequest(url+jsonNode.get("id"), query, HTTP_METHOD.PUT, true);
        Assert.assertTrue(response.statusCode() >= 200 && response.statusCode() <= 299,
                "response is not 200 - 299 :" + response.statusCode());
    }
}
