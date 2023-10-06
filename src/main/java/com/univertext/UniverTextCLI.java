package com.univertext;

import com.univertext.io.reader.FileReaderInterface;
import com.univertext.menu.FileMenuInterface;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class UniverTextCLI {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<FileType, FileReaderInterface> fileReaders;
    private final Map<FileType, FileMenuInterface> fileMenus;

    public UniverTextCLI(Map<FileType, FileReaderInterface> fileReaders,
                         Map<FileType, FileMenuInterface> fileMenus) {
        this.fileReaders = fileReaders;
        this.fileMenus = fileMenus;
    }

    public void start() {
        ConsolePrinter.printLogo("_____  __      _____                   ________           _____ \n" +
                "__  / / /_________(_)__   ________________  __/________  ___  /_\n" +
                "_  / / /__  __ \\_  /__ | / /  _ \\_  ___/_  /  _  _ \\_  |/_/  __/\n" +
                "/ /_/ / _  / / /  / __ |/ //  __/  /   _  /   /  __/_>  < / /_  \n" +
                "\\____/  /_/ /_//_/  _____/ \\___//_/    /_/    \\___//_/|_| \\__/  \n" +
                "\n UniverText is an advanced console-based tool designed for the analysis, editing, and conversion of text and CSV files.\n" +
                "Additionally, it features a built-in scraper for extracting data from the web.");

        do {
            ConsolePrinter.printMenuTitle("\nWelcome in the UniverText!");
            ConsolePrinter.printMenu("""
                    1. Load TEXT File
                    2. Load CSV File
                    3. Load JSON File
                    4. Load XML File
                    5. Exit""");
            ConsolePrinter.print("\nSelect an option: ");

            switch (scanner.nextLine()) {
                case "1" -> loadFile(FileType.TXT);
                case "2" -> loadFile(FileType.CSV);
                case "3" -> loadFile(FileType.JSON);
                case "4" -> loadFile(FileType.XML);
                case "5" -> {
                    ConsolePrinter.print("Exiting... Thank you for using UniverTextCLI!");
                    System.exit(0);
                }
                default -> ConsolePrinter.printError("Invalid option, please try again.");
            }
        } while (true);

    }

    private void loadFile(FileType fileType) {
        while (true) {
            ConsolePrinter.print("Enter the path of the " + fileType.toString() + " file (or type 'return' to return to main menu): ");
            String path = scanner.nextLine();
            if (path.equalsIgnoreCase("return")) {
                return;
            }
            Optional<FileContent> fileContent = fileReaders.get(fileType).readFile(path);
            if (fileContent.isPresent()) {
                ConsolePrinter.printSuccess("File loaded successfully!");
                fileMenus.get(fileType).launchMenu(scanner, fileContent.get());
            } else ConsolePrinter.printError("Failed to load the file. Please check the path and try again.");
        }
    }
}
