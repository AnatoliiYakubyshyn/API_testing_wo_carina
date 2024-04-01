package com.solvd.apitest;

import java.io.FileInputStream;
import java.util.Properties;

public class R {

    public static final String COMMON_FILE_PATH = "src/main/resources/";

    public static final String TEST_DATA_PATH = COMMON_FILE_PATH + "test_data.properties";

    public static String readToken() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(COMMON_FILE_PATH + "api_token.properties"));
            return (String) properties.get("token");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRestUrl() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(TEST_DATA_PATH));
            return (String) properties.get("rest_url");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getGraphQLUrl() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(TEST_DATA_PATH));
            return (String) properties.get("graph_url");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
