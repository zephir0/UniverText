package com.univertext.analyzer;

import com.univertext.model.FileContent;
import com.univertext.printer.ConsolePrinter;

import java.util.Map;
import java.util.stream.Collectors;

public class CsvFileAnalyzer {
    public double calculateSum(FileContent fileContent,
                               int columnIndex) {
        double sum = 0;
        for (String line : fileContent.getLines()) {
            String[] cells = line.split(",");
            try {
                sum += Double.parseDouble(cells[columnIndex]);
            } catch (NumberFormatException e) {
                ConsolePrinter.print(("Skipping non-numeric value: " + cells[columnIndex]));
            }
        }
        return sum;
    }

    public Map<String, Long> countRepeatingWords(FileContent fileContent,
                                                 int columnIndex) {
        return fileContent.getLines().stream()
                .map(line -> parseCsvLine(line)[columnIndex].trim())
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
    }

    private String[] parseCsvLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }

}
