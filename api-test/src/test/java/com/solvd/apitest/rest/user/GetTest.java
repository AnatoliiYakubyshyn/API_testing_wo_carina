package com.solvd.apitest.rest.user;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.apitest.R;

public class GetTest {

    @Test
    public void testPublicUser() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(R.getRestUrl())).GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
