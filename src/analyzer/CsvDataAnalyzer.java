package analyzer;

import model.FileContent;
import printer.ConsolePrinter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvDataAnalyzer implements DataAnalyzerInterface {
    @Override
    public void calculateSum(FileContent fileContent,
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
        ConsolePrinter.print("Sum of column " + columnIndex + ": " + sum);
    }

    @Override
    public void countRepeatingWords(FileContent fileContent,
                                    int columnIndex) {
        Map<String, Long> wordCounts = fileContent.getLines().stream()
                .map(line -> line.split(",")[columnIndex])
                .flatMap(cell -> Arrays.stream(cell.split("\\s+")))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        ConsolePrinter.print("Word counts in column " + columnIndex + ":");
        wordCounts.forEach((word, count) -> ConsolePrinter.print(word + ": " + count));
    }
}
