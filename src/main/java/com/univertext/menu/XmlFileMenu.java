package com.univertext.menu;

import com.univertext.converter.XmlToJsonConverter;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class XmlFileMenu extends FileMenu {
    private final UniversalFileWriter universalFileWriter;
    private final XmlToJsonConverter xmlToJsonConverter;

    public XmlFileMenu(XmlToJsonConverter xmlToJsonConverter,
                       UniversalFileWriter universalFileWriter) {
        this.xmlToJsonConverter = xmlToJsonConverter;
        this.universalFileWriter = universalFileWriter;
    }

    @Override
    public void displayMenu(Scanner scanner,
                            FileContent fileContent) {
        do {
            ConsolePrinter.printMenuTitle("\nJSON File Menu:");
            ConsolePrinter.printMenu("""
                    1. Display file content.
                    2. Convert to JSON.
                    3. Go back.""");
            switch (scanner.nextLine()) {
                case "1" -> displayContent(fileContent);
                case "2" -> {
                    FileContent convertedFile = xmlToJsonConverter.convert(fileContent);
                    universalFileWriter.writeFile(convertedFile, FileType.JSON);
                }
                case "3" -> {
                    ConsolePrinter.print("Returning to main menu.");
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        } while (true);
    }

}
