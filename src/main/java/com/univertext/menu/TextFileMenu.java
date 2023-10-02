package com.univertext.menu;

import com.univertext.converter.TextToCsvConverter;
import com.univertext.editor.TextFileEditor;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class TextFileMenu extends FileMenu {
    private final UniversalFileWriter universalFileWriter;
    private final TextFileEditor textFileEditor;
    private final TextToCsvConverter textToCsvConverter;

    public TextFileMenu(UniversalFileWriter universalFileWriter,
                        TextFileEditor textFileEditor,
                        TextToCsvConverter textToCsvConverter) {
        this.universalFileWriter = universalFileWriter;
        this.textFileEditor = textFileEditor;
        this.textToCsvConverter = textToCsvConverter;
    }

    @Override
    public void displayMenu(Scanner scanner,
                            FileType fileType,
                            FileContent fileContent) {
        do {
            ConsolePrinter.printMenuTitle("\nText File Menu: ");
            ConsolePrinter.printMenu("""
                    1. Display file content.
                    2. Edit file.
                    3. Analyze file.
                    4. Convert to CSV.
                    5. Go back""");
            switch (scanner.nextLine()) {
                case "1" -> displayContent(fileContent);
                case "2" -> editFile(scanner, fileContent);
                case "4" -> {
                    FileContent convertedFile = textToCsvConverter.convert(fileContent);
                    handleFileSave(scanner, convertedFile, universalFileWriter, FileType.CSV);
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
            ConsolePrinter.printMenuTitle("\nText Edit Menu: ");
            ConsolePrinter.printMenu("""
                    1. Remove duplicates.
                    2. Replace words.
                    3. Sort lines alphabetically A-Z.
                    4. Add line numbering.
                    5. Filter lines using a keyword.
                    6. Reverse lines.
                    7. Change encoding.
                    8. Convert to upper case.
                    9. Go back to the menu.""");
            switch (scanner.nextLine()) {
                case "1" -> textFileEditor.removeDuplicates(fileContent);
                case "2" -> {
                    ConsolePrinter.print("Enter the old word you want to change: ");
                    String oldWord = scanner.nextLine();
                    ConsolePrinter.print("Enter the new word you want to change it to: ");
                    String newWord = scanner.nextLine();
                    textFileEditor.replaceWords(fileContent, oldWord, newWord);
                }
                case "3" -> textFileEditor.sortLines(fileContent);
                case "4" -> textFileEditor.addLineNumber(fileContent);
                case "5" -> {
                    ConsolePrinter.print("Enter your keywords (separated by spaces): ");
                    String line = scanner.nextLine();
                    String[] keywords = line.split("\\s+");
                    textFileEditor.filterLines(fileContent, keywords);
                }

                case "6" -> textFileEditor.reverseLines(fileContent);
                case "7" -> {
                    ConsolePrinter.print("Enter new format: ");
                    String codingFormat = scanner.nextLine();
                    textFileEditor.changeEncoding(fileContent, codingFormat);
                }
                case "8" -> textFileEditor.convertToUpperCase(fileContent);
                case "9" -> {
                    return;
                }
                default -> {
                    ConsolePrinter.printError("Invalid option. Please try again.");
                    continue;
                }
            }
            handleFileSave(scanner, fileContent, universalFileWriter, FileType.TXT);
        } while (true);
    }
}

