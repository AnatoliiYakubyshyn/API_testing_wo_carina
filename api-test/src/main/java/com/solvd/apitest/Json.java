package com.solvd.apitest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parseString(String jsonText) throws JsonProcessingException {
        return objectMapper.readTree(jsonText);
    }
}
