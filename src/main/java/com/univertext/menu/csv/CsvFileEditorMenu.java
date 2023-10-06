package com.univertext.menu.csv;

import com.univertext.editor.CsvFileEditor;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.menu.FileMenuBase;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class CsvFileEditorMenu extends FileMenuBase {
    private final CsvFileEditor csvFileEditor;
    private final UniversalFileWriter universalFileWriter;

    public CsvFileEditorMenu(CsvFileEditor csvFileEditor,
                             UniversalFileWriter universalFileWriter) {
        this.csvFileEditor = csvFileEditor;
        this.universalFileWriter = universalFileWriter;
    }

    @Override
    public void launchMenu(Scanner scanner,
                           FileContent fileContent) {
        do {
            ConsolePrinter.printMenuTitle("\nCSV Edit Menu: ");
            ConsolePrinter.printMenu("""
                    1. Sort columns.
                    2. Remove duplicates.
                    3. Return to CSV menu.""");
            switch (scanner.nextLine()) {
                case "1" -> csvFileEditor.sortLines(fileContent);
                case "2" -> csvFileEditor.removeDuplicates(fileContent);
                case "3" -> {
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
            handleFileSave(scanner, fileContent, universalFileWriter, FileType.CSV);
        } while (true);
    }
}
