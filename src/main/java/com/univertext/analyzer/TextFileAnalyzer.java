package com.univertext.analyzer;

import com.univertext.model.FileContent;

import java.util.Arrays;

public class TextFileAnalyzer {
    public int countLines(FileContent fileContent) {
        return fileContent.getLines().size();
    }

    public long countWords(FileContent fileContent) {
        return fileContent.getLines().stream()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .count();
    }

    public int countCharacters(FileContent fileContent) {
        return fileContent.getLines().stream().mapToInt(String::length).sum();
    }
}
