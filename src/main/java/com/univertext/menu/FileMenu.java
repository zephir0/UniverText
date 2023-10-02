package com.univertext.menu;

import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Scanner;

public abstract class FileMenu implements FileMenuInterface {
    protected void handleFileSave(Scanner scanner,
                                  FileContent fileContent,
                                  UniversalFileWriter universalFileWriter,
                                  FileType fileType) {
        do {
            ConsolePrinter.print("Do you want to save the file, show the content, or continue editing? (save/show/continue): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "save" -> {
                    universalFileWriter.writeFile(fileContent, fileType);
                    return;
                }
                case "show" -> displayContent(fileContent);
                case "continue" -> {
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        } while (true);
    }

    protected void displayContent(FileContent fileContent) {
        fileContent.getLines().forEach(ConsolePrinter::print);
    }
}

