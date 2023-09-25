package editor;

import model.FileContent;
import printer.ConsolePrinter;

import java.util.List;
import java.util.stream.Collectors;

public class TextFileEditor extends AbstractFileEditor {
    public void changeEncoding(FileContent fileContent,
                               String newEncoding) {
        ConsolePrinter.print("Encoding changed.");
    }

    public void convertToUpperCase(FileContent fileContent) {
        List<String> updatedLines = fileContent.getLines().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        fileContent.setLines(updatedLines);
        ConsolePrinter.print("Converted to upper case.");
    }
}
