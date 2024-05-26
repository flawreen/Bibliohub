package org.bibliohub.service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuditService {
    private File csvOutputFile;

    public AuditService() {
        this.csvOutputFile = new File("D:\\FMI\\An 2\\Sem 2\\Bibliohub\\src\\main\\java\\org\\bibliohub\\service\\audit_files\\audit.csv");
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    // Escape special characters in a CSV field
    public String escapeSpecialCharacters(String data) {
        if (data == null) {
            return "";
        }
        String escapedData = data.replaceAll("\\R", " ");  // \n inlocuit cu ' '
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public void append(List<String[]> dataLines) {
        try (FileOutputStream fos = new FileOutputStream(csvOutputFile, true);
             Writer writer = new OutputStreamWriter(fos);
             PrintWriter pw = new PrintWriter(writer)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (IOException e) {
        }
    }
}
