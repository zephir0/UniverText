package com.univertext.menu.json;

import com.univertext.converter.JsonToXmlConverter;
import com.univertext.converter.JsonToYamlConverter;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.menu.FileMenuBase;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public class JsonFileMenu extends FileMenuBase {
    private final UniversalFileWriter universalFileWriter;
    private final JsonToXmlConverter jsonToXmlConverter;
    private final JsonToYamlConverter jsonToYamlConverter;

    public JsonFileMenu(UniversalFileWriter universalFileWriter,
                        JsonToXmlConverter jsonToXmlConverter,
                        JsonToYamlConverter jsonToYamlConverter) {
        this.universalFileWriter = universalFileWriter;
        this.jsonToXmlConverter = jsonToXmlConverter;
        this.jsonToYamlConverter = jsonToYamlConverter;
    }

    @Override
    public void launchMenu(Scanner scanner,
                            FileContent fileContent) {
        do {
            ConsolePrinter.printMenuTitle("\nJSON File Menu:");
            ConsolePrinter.printMenu("""
                    1. Display file content.
                    2. Convert to XML.
                    3. Convert to YAML.
                    4. Go back.""");
            switch (scanner.nextLine()) {
                case "1" -> displayContent(fileContent);
                case "2" -> {
                    FileContent convertedFile = jsonToXmlConverter.convert(fileContent);
                    universalFileWriter.writeFile(convertedFile, FileType.XML);
                }
                case "3" -> {
                    FileContent convertedFile = jsonToYamlConverter.convert(fileContent);
                    universalFileWriter.writeFile(convertedFile, FileType.YAML);
                }
                case "4" -> {
                    ConsolePrinter.print("Returning to main menu.");
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        } while (true);
    }

}
