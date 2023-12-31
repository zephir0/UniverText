package com.univertext.menu.csv;

import com.univertext.converter.CsvToTextConverter;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.menu.FileMenuBase;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class CsvFileMenu extends FileMenuBase {
    private final UniversalFileWriter universalFileWriter;
    private final CsvFileAnalyzerMenu csvFileAnalyzerMenu;
    private final CsvFileEditorMenu csvFileEditorMenu;
    private final CsvToTextConverter csvToTextConverter;

    public CsvFileMenu(UniversalFileWriter universalFileWriter,
                       CsvFileAnalyzerMenu csvFileAnalyzerMenu,
                       CsvFileEditorMenu csvFileEditorMenu,
                       CsvToTextConverter csvToTextConverter) {
        this.universalFileWriter = universalFileWriter;
        this.csvFileAnalyzerMenu = csvFileAnalyzerMenu;
        this.csvFileEditorMenu = csvFileEditorMenu;
        this.csvToTextConverter = csvToTextConverter;
    }


    @Override
    public void launchMenu(Scanner scanner,
                           FileContent fileContent) {
        do {
            ConsolePrinter.printMenuTitle("\nCSV File Menu:");
            ConsolePrinter.printMenu("""
                    1. Display file content.
                    2. Edit file.
                    3. Analyze file.
                    4. Convert to Text.
                    5. Go back.""");
            switch (scanner.nextLine()) {
                case "1" -> displayContent(fileContent);
                case "2" -> csvFileEditorMenu.launchMenu(scanner, fileContent);
                case "3" -> csvFileAnalyzerMenu.launchMenu(scanner, fileContent);
                case "4" -> {
                    FileContent convertedFile = csvToTextConverter.convert(fileContent);
                    handleFileSave(scanner, convertedFile, universalFileWriter, FileType.TXT);
                }
                case "5" -> {
                    ConsolePrinter.print("Returning to main menu.");
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        } while (true);
    }
}
