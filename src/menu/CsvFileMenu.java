package menu;

import analyzer.CsvDataAnalyzer;
import editor.CsvFileEditor;
import io.writer.CsvFileWriter;
import model.FileContent;
import model.FileType;

import java.util.Scanner;

public class CsvFileMenu implements FileMenuInterface {
    private Scanner scanner;
    private FileContent currentFileContent;
    private final CsvDataAnalyzer dataAnalyzer = new CsvDataAnalyzer();
    private final CsvFileWriter csvFileWriter = new CsvFileWriter();
    private final CsvFileEditor csvFileEditor = new CsvFileEditor();

    @Override
    public void displayMenu(Scanner scanner,
                            FileType fileType,
                            FileContent currentFileContent) {
        this.scanner = scanner;
        this.currentFileContent = currentFileContent;

        while (true) {
            System.out.println("CSV File Menu:");
            System.out.println("1. Display file content.");
            System.out.println("2. Edit file.");
            System.out.println("3. Analyze file.");
            System.out.println("4. Convert to Text.");
            System.out.println("5. Return to main menu.");
            switch (scanner.nextLine()) {
                case "1":
                    displayContent();
                    break;
                case "2":
                    editFile();
                    break;
                case "3":
                    analyzeFile();
                    break;
                case "4":
                    convertToText();
                    break;
                case "5":
                    System.out.println("Returning to main menu.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayContent() {
        currentFileContent.getLines().forEach(System.out::println);
    }

    private void editFile() {
        System.out.println("1. Sort columns.");
        System.out.println("2. Remove duplicates.");
        System.out.println("3. Return to CSV menu.");
        String editOption = scanner.nextLine();

        if (editOption.equals("1")) {
            int columnIndex = promptForColumnIndex();
            if (columnIndex == -1) return;
            csvFileEditor.sortColumn(currentFileContent, columnIndex);
            csvFileWriter.writeFile(currentFileContent);
        } else if (editOption.equals("2")) {
            csvFileEditor.removeDuplicates(currentFileContent);
            csvFileWriter.writeFile(currentFileContent);
        }
    }


    private void analyzeFile() {
        System.out.println("1. Calculate sum from a column.");
        System.out.println("2. Count repeating words in a column.");
        System.out.println("3. Return to CSV menu.");
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
        System.out.println("CSV converted to Text.");
    }

    private int promptForColumnIndex() {
        System.out.println("Enter the column index:");
        int columnIndex = Integer.parseInt(scanner.nextLine());
        if (columnIndex < 0 || columnIndex >= getColumnCount()) {
            System.out.println("Invalid column index.");
            return -1;
        }
        return columnIndex;
    }

    private int getColumnCount() {
        return currentFileContent.getLines().get(0).split(",").length;
    }

}
