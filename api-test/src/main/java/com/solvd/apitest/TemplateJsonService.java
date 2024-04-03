package com.solvd.apitest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemplateJsonService {

    public static String readAndFillTemplate(String path, Object... args) throws IOException {
        return String.format(Files.readString(Path.of(path), StandardCharsets.UTF_8), args);
    }
}
