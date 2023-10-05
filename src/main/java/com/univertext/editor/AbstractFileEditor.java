package com.univertext.editor;

import com.univertext.model.FileContent;
import com.univertext.printer.ConsolePrinter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractFileEditor {
    public void removeDuplicates(FileContent fileContent) {
        List<String> uniqueLines = fileContent.getLines().stream()
                .distinct()
                .collect(Collectors.toList());
        fileContent.setLines(uniqueLines);
        ConsolePrinter.printSuccess("Duplicates removed.");
    }

    public void replaceWords(FileContent fileContent,
                             String oldWord,
                             String newWord) {
        if (fileContent.getLines().stream().noneMatch(line -> line.contains(oldWord))) {
            ConsolePrinter.printError("There is no lines with keyword: " + oldWord);
        } else {
            fileContent.getLines().replaceAll(line -> line.replaceAll(oldWord, newWord));
            ConsolePrinter.printSuccess("Words replaced.");
        }
    }

    public void sortLines(FileContent fileContent) {
        List<String> sortedLines = fileContent.getLines().stream()
                .sorted()
                .collect(Collectors.toList());
        fileContent.setLines(sortedLines);
        ConsolePrinter.printSuccess("Lines sorted.");
    }

    public void addLineNumber(FileContent fileContent) {
        List<String> numberedLines = IntStream.range(0, fileContent.getLines().size())
                .mapToObj(index -> (index + 1) + ". " + fileContent.getLines().get(index))
                .collect(Collectors.toList());
        fileContent.setLines(numberedLines);
        ConsolePrinter.printSuccess("Line numbers added.");
    }

    public void filterLines(FileContent fileContent,
                            String[] keywords) {
        List<String> filteredLines = fileContent.getLines().stream()
                .filter(line -> Arrays.stream(keywords).anyMatch(line::contains))
                .collect(Collectors.toList());
        fileContent.setLines(filteredLines);
        ConsolePrinter.printSuccess("Lines filtered.");
    }

    public void reverseLines(FileContent fileContent) {
        List<String> reversedLines = fileContent.getLines().stream()
                .map(line -> new StringBuilder(line).reverse().toString())
                .collect(Collectors.toList());
        fileContent.setLines(reversedLines);
        ConsolePrinter.printSuccess("Lines reversed.");
    }
}
