package com.univertext.menu.text;

import com.univertext.converter.TextToCsvConverter;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.menu.FileMenuBase;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class TextFileMenu extends FileMenuBase {
    private final UniversalFileWriter universalFileWriter;
    private final TextFileAnalyzerMenu textFileAnalyzerMenu;
    private final TextFileEditorMenu textFileEditorMenu;
    private final TextToCsvConverter textToCsvConverter;

    public TextFileMenu(
            UniversalFileWriter universalFileWriter,
            TextFileAnalyzerMenu textFileAnalyzerMenu,
            TextFileEditorMenu textFileEditorMenu,
            TextToCsvConverter textToCsvConverter) {
        this.universalFileWriter = universalFileWriter;
        this.textFileAnalyzerMenu = textFileAnalyzerMenu;
        this.textFileEditorMenu = textFileEditorMenu;
        this.textToCsvConverter = textToCsvConverter;
    }

    @Override
    public void launchMenu(Scanner scanner,
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
                case "2" -> textFileEditorMenu.launchMenu(scanner, fileContent);
                case "3" -> textFileAnalyzerMenu.launchMenu(scanner, fileContent);
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
}


