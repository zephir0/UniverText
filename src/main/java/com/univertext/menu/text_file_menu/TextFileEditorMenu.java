package com.univertext.menu.text_file_menu;

import com.univertext.editor.TextFileEditor;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.menu.FileMenu;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class TextFileEditorMenu extends FileMenu {
    private final TextFileEditor textFileEditor;
    private final UniversalFileWriter universalFileWriter;

    public TextFileEditorMenu(TextFileEditor textFileEditor,
                              UniversalFileWriter universalFileWriter) {
        this.textFileEditor = textFileEditor;
        this.universalFileWriter = universalFileWriter;
    }

    @Override
    public void displayMenu(Scanner scanner,
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
