package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.solvd.apitest.TemplateJsonService;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.apitest.Json;
import com.solvd.apitest.RequestService;
import com.solvd.apitest.R;

public class GetTest {

    private final String path = "src/test/resources/response.json";

    @Test
    public void testPublicUser() throws IOException, InterruptedException {
        HttpResponse<String> response = RequestService.getRequestWithNoBody(R.getRestUrl(), false);
        Assert.assertEquals(response.statusCode(), 200,"Actual result: " +response.statusCode());
    }

    @Test
    public void testSpecificUser() throws IOException, InterruptedException {
        HttpResponse<String> response = RequestService.getRequestWithNoBody(R.getRestUrl() + "6777744", false);
        if (response.statusCode() == 200) {
            Assert.assertEquals(Json.parseString(response.body()),
                    Json.parseString(TemplateJsonService.readAndFillTemplate(path, 6777744, "Divya Jha",
                            "divya_jha@corkery.example", "male", "active")));
        } else {
            Assert.assertEquals(response.statusCode(), 404,"Actual result: " +response.statusCode());
        }
    }

    @Test
    public void testUnavailableUser() throws IOException, InterruptedException {
        HttpResponse<String> response = RequestService.getRequestWithNoBody(R.getRestUrl() + "67777440", false);
        Assert.assertEquals(response.statusCode(), 404,"Actual result: " +response.statusCode());
    }
}
