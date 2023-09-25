package editor;

import model.FileContent;
import printer.ConsolePrinter;

import java.util.List;
import java.util.stream.Collectors;

public class CsvFileEditor extends AbstractFileEditor {
    public void changeDelimiter(FileContent fileContent,
                                String newDelimiter) {
        List<String> updatedLines = fileContent.getLines().stream()
                .map(line -> line.replace(",", newDelimiter))
                .collect(Collectors.toList());
        fileContent.setLines(updatedLines);
        ConsolePrinter.print("Delimiter changed.");
    }

    public void transformToTable(FileContent fileContent) {
        fileContent.getLines().forEach(line -> {
            String[] columns = line.split(",");
            System.out.println(String.join("\t", columns));
        });
    }

    public void addColumn(FileContent fileContent,
                          String columnName) {
        List<String> updatedLines = fileContent.getLines().stream()
                .map(line -> line + "," + columnName)
                .collect(Collectors.toList());
        fileContent.setLines(updatedLines);
        ConsolePrinter.print("Column added.");
    }
}