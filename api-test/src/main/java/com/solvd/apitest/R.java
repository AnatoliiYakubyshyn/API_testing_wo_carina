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
}
