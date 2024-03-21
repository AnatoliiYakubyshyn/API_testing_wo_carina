package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import com.solvd.apitest.enums.HTTP_METHOD;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.apitest.R;
import com.solvd.apitest.RequestService;

public class DeleteTest {

    @Test
    private void testCreatedUserIsDeleted() throws IOException, InterruptedException {
        String body = "{\n" +
                "\"name\":\"Frederic Federo\",\n" +
                "\"email\":\"frederik_federo@dom.example\",\n" +
                "\"gender\":\"male\",\n" +
                "\"status\":\"inactive\"\n" +
                "} ";
        HttpResponse<String> response = RequestService.changeRequest(R.getRestUrl(), body, HTTP_METHOD.POST,
                true);
        Assert.assertEquals(response.statusCode(), 201);
        response = RequestService.getRequestWithNoBody(R.getRestUrl(),
                true);
        JsonNode jsonNode = Json.parseString(response.body());
        boolean f = false;
        String id = "";
        for (int i = 0; i < jsonNode.size(); i++) {
            System.out.println(jsonNode.get(i).get("name").toString());
            if (jsonNode.get(i).get("name").toString().equals("Frederic Federo")) {
                f = true;
                id = jsonNode.get(i).get("id").toString();
                break;
            }
        }
        Assert.assertTrue(f, "No such person. Data is not saved");
        response = RequestService.delete(R.getRestUrl() + id, true);
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204);
    }
}
