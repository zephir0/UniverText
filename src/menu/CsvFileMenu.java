package menu;

import analyzer.CsvDataAnalyzer;
import editor.CsvFileEditor;
import io.writer.CsvFileWriter;
import model.FileContent;
import model.FileType;
import printer.ConsolePrinter;

import java.util.Scanner;

public class CsvFileMenu implements FileMenuInterface {
    private final CsvDataAnalyzer dataAnalyzer = new CsvDataAnalyzer();
    private final CsvFileWriter csvFileWriter = new CsvFileWriter();
    private final CsvFileEditor csvFileEditor = new CsvFileEditor();
    private Scanner scanner;
    private FileContent currentFileContent;

    @Override
    public void displayMenu(Scanner scanner,
                            FileType fileType,
                            FileContent currentFileContent) {
        this.scanner = scanner;
        this.currentFileContent = currentFileContent;

        while (true) {
            ConsolePrinter.printMenu("""
                    \n CSV File Menu:
                    1. Display file content.
                    2. Edit file.
                    3. Analyze file.
                    4. Convert to Text.
                    5. Return to main menu.""");

            switch (scanner.nextLine()) {
                case "1" -> displayContent();
                case "2" -> editFile();
                case "3" -> analyzeFile();
                case "4" -> convertToText();
                case "5" -> {
                    ConsolePrinter.print("Returning to main menu.");
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        }
    }

    private void displayContent() {
        currentFileContent.getLines().forEach(ConsolePrinter::print);
    }

    private void editFile() {
        ConsolePrinter.printMenu("""
                \n1. Sort columns.
                2. Remove duplicates.
                3. Return to CSV menu.""");
        String editOption = scanner.nextLine();
        if (editOption.equals("1")) {
            csvFileEditor.sortLines(currentFileContent);
            csvFileWriter.writeFile(currentFileContent);
        } else if (editOption.equals("2")) {
            csvFileEditor.removeDuplicates(currentFileContent);
            csvFileWriter.writeFile(currentFileContent);
        }
    }


    private void analyzeFile() {
        ConsolePrinter.printMenu("""
                \n1. Calculate sum from a column.
                2. Count repeating words in a column.
                3. Return to CSV menu.""");
        String analyzeOption = scanner.nextLine();
        int columnIndex = promptForColumnIndex();
        if (columnIndex == -1) return;
        if (analyzeOption.equals("1")) {
            dataAnalyzer.calculateSum(currentFileContent, columnIndex);
        } else if (analyzeOption.equals("2")) {
            dataAnalyzer.countRepeatingWords(currentFileContent, columnIndex);
        }
    }

    private void convertToText() {
        ConsolePrinter.print("CSV converted to Text.");
    }

    private int promptForColumnIndex() {
        ConsolePrinter.print("Enter the column index:");
        int columnIndex = Integer.parseInt(scanner.nextLine());
        if (columnIndex < 0 || columnIndex >= getColumnCount()) {
            ConsolePrinter.printError("Invalid column index.");
            return -1;
        }
        return columnIndex;
    }

    private int getColumnCount() {
        return currentFileContent.getLines().get(0).split(",").length;
    }

}
