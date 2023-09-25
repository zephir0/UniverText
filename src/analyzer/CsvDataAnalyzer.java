package analyzer;

import model.FileContent;
import java.util.*;
import java.util.stream.Collectors;

public class CsvDataAnalyzer implements DataAnalyzerInterface {
    @Override
    public void calculateSum(FileContent fileContent, int columnIndex) {
        double sum = 0;
        for (String line : fileContent.getLines()) {
            String[] cells = line.split(",");
            try {
                sum += Double.parseDouble(cells[columnIndex]);
            } catch (NumberFormatException e) {
                System.out.println("Skipping non-numeric value: " + cells[columnIndex]);
            }
        }
        System.out.println("Sum of column " + columnIndex + ": " + sum);
    }

    @Override
    public void countRepeatingWords(FileContent fileContent, int columnIndex) {
        Map<String, Long> wordCounts = fileContent.getLines().stream()
                .map(line -> line.split(",")[columnIndex])
                .flatMap(cell -> Arrays.stream(cell.split("\\s+")))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        System.out.println("Word counts in column " + columnIndex + ":");
        wordCounts.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}
