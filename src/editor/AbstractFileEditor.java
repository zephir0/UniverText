package editor;

import model.FileContent;
import printer.ConsolePrinter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractFileEditor {

    public void removeDuplicates(FileContent fileContent) {
        List<String> uniqueLines = fileContent.getLines().stream().distinct().collect(Collectors.toList());
        fileContent.setLines(uniqueLines);
        ConsolePrinter.print("Duplicates removed.");
    }

    public void replaceWords(FileContent fileContent,
                             String oldWord,
                             String newWord) {
        List<String> updatedLines = fileContent.getLines().stream()
                .map(line -> line.replaceAll(oldWord, newWord))
                .collect(Collectors.toList());
        fileContent.setLines(updatedLines);
        ConsolePrinter.print("Words replaced.");
    }

    public void sortLines(FileContent fileContent) {
        List<String> sortedLines = fileContent.getLines().stream().sorted().collect(Collectors.toList());
        fileContent.setLines(sortedLines);
        ConsolePrinter.print("Lines sorted.");
    }

    public void addLineNumber(FileContent fileContent) {
        List<String> numberedLines = IntStream.range(0, fileContent.getLines().size())
                .mapToObj(index -> (index + 1) + ". " + fileContent.getLines().get(index))
                .collect(Collectors.toList());
        fileContent.setLines(numberedLines);
        ConsolePrinter.print("Line numbers added.");
    }

    public void filterLines(FileContent fileContent,
                            String keyword) {
        List<String> filteredLines = fileContent.getLines().stream()
                .filter(line -> line.contains(keyword))
                .collect(Collectors.toList());
        fileContent.setLines(filteredLines);
        ConsolePrinter.print("Lines filtered.");
    }

    public void reverseLines(FileContent fileContent) {
        List<String> reversedLines = fileContent.getLines().stream()
                .map(line -> new StringBuilder(line).reverse().toString())
                .collect(Collectors.toList());
        fileContent.setLines(reversedLines);
        ConsolePrinter.print("Lines reversed.");
    }
}
