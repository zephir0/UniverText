package editor;

import model.FileContent;

import java.util.stream.Collectors;

public class CsvFileEditor implements EditorInterface {
    @Override
    public void sortColumn(FileContent fileContent,
                           int columnIndex) {
        fileContent.getLines().sort((o1, o2) -> o1.split(",")[columnIndex].compareTo(o2.split(",")[columnIndex]));
        System.out.println("Column sorted.");
    }

    @Override
    public void removeDuplicates(FileContent fileContent) {
        fileContent.setLines(fileContent.getLines().stream().distinct().collect(Collectors.toList()));
        System.out.println("Duplicates removed.");
    }
}
