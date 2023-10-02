package com.univertext.menu;

import com.univertext.analyzer.CsvDataAnalyzer;
import com.univertext.converter.CsvToTextConverter;
import com.univertext.editor.CsvFileEditor;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class CsvFileMenu extends FileMenu {
    private final UniversalFileWriter universalFileWriter;
    private final CsvFileEditor csvFileEditor;
    private final CsvDataAnalyzer dataAnalyzer;
    private final CsvToTextConverter csvToTextConverter;

    public CsvFileMenu(UniversalFileWriter universalFileWriter,
                       CsvFileEditor csvFileEditor,
                       CsvDataAnalyzer dataAnalyzer,
                       CsvToTextConverter csvToTextConverter) {
        this.universalFileWriter = universalFileWriter;
        this.csvFileEditor = csvFileEditor;
        this.dataAnalyzer = dataAnalyzer;
        this.csvToTextConverter = csvToTextConverter;
    }

    @Override
    public void displayMenu(Scanner scanner,
                            FileType fileType,
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
                case "2" -> editFile(scanner, fileContent);
                case "3" -> analyzeFile(scanner, fileContent);
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


    public void editFile(Scanner scanner,
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
                default -> {
                    ConsolePrinter.printError("Invalid option. Please try again.");
                    continue;
                }
            }
            handleFileSave(scanner, fileContent, universalFileWriter, FileType.CSV);
        } while (true);

    }


    public void analyzeFile(Scanner scanner,
                            FileContent fileContent) {
        do {
            ConsolePrinter.printMenuTitle("\nCSV Analyze Menu: ");
            ConsolePrinter.printMenu("""
                    1. Calculate sum from a column.
                    2. Count repeating words in a column.
                    3. Return to CSV menu.""");
            switch (scanner.nextLine()) {
                case "1" -> {
                    int columnIndex = promptForColumnIndex(scanner, fileContent);
                    dataAnalyzer.calculateSum(fileContent, columnIndex);
                }
                case "2" -> {
                    int columnIndex = promptForColumnIndex(scanner, fileContent);
                    dataAnalyzer.countRepeatingWords(fileContent, columnIndex);
                }
                case "3" -> {
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        } while (true);
    }

    private int promptForColumnIndex(Scanner scanner,
                                     FileContent fileContent) {
        while (true) {
            ConsolePrinter.print("Enter the column index:");
            try {
                int columnIndex = Integer.parseInt(scanner.nextLine());
                if (columnIndex < 0 || columnIndex >= getColumnCount(fileContent)) {
                    ConsolePrinter.printError("Invalid column index. Please enter a valid index.");
                } else {
                    return columnIndex;
                }
            } catch (NumberFormatException e) {
                ConsolePrinter.printError("Invalid input. Please enter a valid number.");
            }
        }
    }


    private int getColumnCount(FileContent fileContent) {
        return fileContent.getLines().get(0).split(",").length;
    }

}
