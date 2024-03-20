package com.solvd.apitest;

import java.io.FileInputStream;
import java.util.Properties;

public class R {

    public static String readToken() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/api_token.properties"));
            return (String) properties.get("token");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRestUrl() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/test_data.properties"));
            return (String) properties.get("rest_url");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getGraphQLUrl() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/test_data.properties"));
            return (String) properties.get("graph_url");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
