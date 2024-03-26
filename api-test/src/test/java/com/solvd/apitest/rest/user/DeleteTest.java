package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.solvd.apitest.TemplateJsonService;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import com.solvd.apitest.enums.HTTP_METHOD;
import com.solvd.apitest.R;
import com.solvd.apitest.RequestService;

public class DeleteTest {

    @Test
    private void testCreatedUserIsDeleted() throws IOException, InterruptedException {
        String body = TemplateJsonService.readAndFillTemplate("src/test/resources/post.json","Federic Federo",
                "federik_federo@dom.example","male","inactive");
        HttpResponse<String> response = RequestService.changeRequest(R.getRestUrl(), body, HTTP_METHOD.POST,
                true);
        Assert.assertEquals(response.statusCode(), 201);
        response = RequestService.getRequestWithNoBody(R.getRestUrl(),
                true);
        JsonNode jsonNode = Json.parseString(response.body());
        boolean f = false;
        String id = "";
        for (int i = 0; i < jsonNode.size(); i++) {
            if (jsonNode.get(i).get("name").toString().equals("\"Federic Federo\"")) {
                f = true;
                id = jsonNode.get(i).get("id").toString();
                break;
            }
        }
        Assert.assertTrue(f, "No such person. Data is not saved");
        response = RequestService.delete(R.getRestUrl() + id, true);
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204);
    }

    @Test
    public void testDeleteNotExistingUser() throws IOException, InterruptedException {
        HttpResponse<String> response = RequestService.delete(R.getRestUrl() + 67777440, true);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test
    public void testDeleteWithoutAuth() throws IOException, InterruptedException {
        HttpResponse<String> response = RequestService.delete(R.getRestUrl() + 6777744, false);
        Assert.assertTrue(response.statusCode() == 401 || response.statusCode() == 404);
    }
}
