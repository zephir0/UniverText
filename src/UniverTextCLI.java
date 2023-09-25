import io.reader.FileReaderInterface;
import menu.FileMenuInterface;
import model.FileContent;
import model.FileType;
import printer.ConsolePrinter;

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

                "Welcome in the UniverText! \n" +

                "\n UniverText is an advanced console-based tool designed for the analysis, editing, and conversion of text and CSV files.\n" +
                "Additionally, it features a built-in scraper for extracting data from the web.");

        while (true) {
            ConsolePrinter.printMenu("""
                    \n1. Load Text File
                    2. Load CSV File
                    3. TODO
                    4. Exit""");
            ConsolePrinter.print("\nSelect an option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    loadFile(FileType.TEXT);
                    break;
                case "2":
                    loadFile(FileType.CSV);
                    break;
                case "3":
                    break;
                case "4":
                    ConsolePrinter.print("Exiting... Thank you for using UniverTextCLI!");
                    System.exit(0);
                    break;
                default:
                    ConsolePrinter.printError("Invalid option, please try again.");
            }
        }
    }

    private void loadFile(FileType fileType) {
        while (true) {
            ConsolePrinter.print("Enter the path of the file: ");
            String path = scanner.nextLine();
            if (path.equalsIgnoreCase("exit") || path.equalsIgnoreCase("return")) {
                return;
            }
            Optional<FileContent> fileContent = fileReaders.get(fileType).readFile(path);
            if (fileContent.isPresent()) {
                FileContent currentFileContent = fileContent.get();
                ConsolePrinter.print("File loaded successfully!");
                fileMenus.get(fileType).displayMenu(scanner, fileType, currentFileContent);
            } else ConsolePrinter.printError("Failed to load the file. Please check the path and try again.");
        }
    }
}
