package com.univertext.menu.csv_file_menu;

import com.univertext.analyzer.CsvFileAnalyzer;
import com.univertext.menu.FileMenuInterface;
import com.univertext.model.FileContent;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class CsvFileAnalyzerMenu implements FileMenuInterface {
    private final CsvFileAnalyzer csvFileAnalyzer;

    public CsvFileAnalyzerMenu(CsvFileAnalyzer csvFileAnalyzer) {
        this.csvFileAnalyzer = csvFileAnalyzer;
    }

    @Override
    public void displayMenu(Scanner scanner,
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
                    csvFileAnalyzer.calculateSum(fileContent, columnIndex);
                }
                case "2" -> {
                    int columnIndex = promptForColumnIndex(scanner, fileContent);
                    csvFileAnalyzer.countRepeatingWords(fileContent, columnIndex);
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
