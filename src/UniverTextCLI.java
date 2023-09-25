import io.reader.FileReaderInterface;
import menu.FileMenuInterface;
import model.FileContent;
import model.FileType;

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
        System.out.println("_____  __      _____                   ________           _____ \n" +
                "__  / / /_________(_)__   ________________  __/________  ___  /_\n" +
                "_  / / /__  __ \\_  /__ | / /  _ \\_  ___/_  /  _  _ \\_  |/_/  __/\n" +
                "/ /_/ / _  / / /  / __ |/ //  __/  /   _  /   /  __/_>  < / /_  \n" +
                "\\____/  /_/ /_//_/  _____/ \\___//_/    /_/    \\___//_/|_| \\__/  \n" +
                "Welcome in the UniverText! \n" +
                "\n UniverText is an advanced console-based tool designed for the analysis, editing, and conversion of text and CSV files.\n" +
                "Additionally, it features a built-in scraper for extracting data from the web.\n");

        while (true) {
            System.out.println("1. Load Text File");
            System.out.println("2. Load CSV File");
            System.out.println("3. Display File Content");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
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
                    System.out.println("Exiting... Thank you for using UniverTextCLI!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void loadFile(FileType fileType) {
        while (true) {
            System.out.print("Enter the path of the file: ");
            String path = scanner.nextLine();
            if (path.equalsIgnoreCase("exit") || path.equalsIgnoreCase("return")) {
                return;
            }
            Optional<FileContent> fileContent = fileReaders.get(fileType).readFile(path);
            if (fileContent.isPresent()) {
                FileContent currentFileContent = fileContent.get();
                System.out.println("File loaded successfully!");
                fileMenus.get(fileType).displayMenu(scanner, fileType, currentFileContent);
            } else System.out.println("Failed to load the file. Please check the path and try again.");
        }
    }
}
