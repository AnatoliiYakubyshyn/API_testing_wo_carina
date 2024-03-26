package com.solvd.apitest.graphql.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.solvd.apitest.Json;
import com.solvd.apitest.R;
import com.solvd.apitest.RequestService;
import com.solvd.apitest.TemplateJsonService;
import com.solvd.apitest.enums.HTTP_METHOD;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

public class MutationUserTest {

    @Test
    public void testReceivingErrorMessageForExistingUser() throws IOException, InterruptedException {
        String body = TemplateJsonService.readAndFillTemplate("src/test/resources/graphql/createUser.json",
                "dssfjksfj","Tolia","wewewe@gmail.com","male","active");
        HttpResponse<String> response = RequestService.changeRequest(R.getGraphQLUrl(),
               body, HTTP_METHOD.POST, true);
        System.out.println(response.body());
        JsonNode jsonNode = Json.parseString(response.body());
        Assert.assertNotNull(jsonNode.get("errors"), response.body());
    }
}
