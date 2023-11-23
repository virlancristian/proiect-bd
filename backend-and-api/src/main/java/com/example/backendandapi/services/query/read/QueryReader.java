package com.example.backendandapi.services.query.read;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryReader {
    File queryFile;
    final String VARIABLE_REGEX = "\\{\\{([^{}]+)\\}\\}";

    public QueryReader(String fileName) {
        queryFile = new File(Paths.get(System.getProperty("user.dir")) + "/queries/" + fileName + ".sql");
    }

    public String getQuery(String replacement) {
        String query = "";

        try {
            String queryOriginal = FileUtils.readFileToString(queryFile, "UTF-8");

            Pattern pattern = Pattern.compile(VARIABLE_REGEX);
            Matcher matcher = pattern.matcher(queryOriginal);

            query = matcher.replaceAll(replacement);
        } catch(IOException error) {
            System.out.println("Unable to read contents of file" + error);
        }

        return query;
    }
}
