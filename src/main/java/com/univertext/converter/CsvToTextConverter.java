package com.univertext.converter;

import com.univertext.model.FileContent;

import java.util.List;
import java.util.stream.Collectors;

public class CsvToTextConverter implements FileConverter {
    @Override
    public FileContent convert(FileContent fileContent) {
        List<String> convertedLines = fileContent.getLines().stream()
                .map(line -> String.join(" ", line.split(",")))
                .collect(Collectors.toList());
        return new FileContent(fileContent.getPath(), convertedLines);
    }
}
