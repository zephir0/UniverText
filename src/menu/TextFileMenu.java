package menu;

import model.FileContent;
import model.FileType;

import java.util.Scanner;

public class TextFileMenu implements FileMenuInterface {
    @Override
    public void displayMenu(Scanner scanner,
                            FileType fileType,
                            FileContent fileContent) {
        while (true) {
            System.out.println("1. Display file content.");
            System.out.println("2. Edit file.");
            System.out.println("3. Analyze file.");
            System.out.println("4. Convert to CSV.");
            System.out.println("5. Return to main menu.");
            switch (scanner.nextLine()) {
                case "1":
                    fileContent.getLines().forEach(System.out::println);
                    break;
                case "2":
                    editFile(scanner);
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void editFile(Scanner scanner) {
        System.out.println("1. Sort alphabetically A-Z.");
        System.out.println("2. Sort alphabetically Z-A.");
        System.out.println("3. Replace word.");
        System.out.println("4. Remove duplicates.");
        System.out.println("5. Go back.");
        while (true) {
            System.out.print("Select an option: ");
            switch (scanner.nextLine()) {
                case "1":
                    // TODO: 9/24/2023
                    break;
                case "2":
                    break;
                case "3":
                    System.out.println("");
                    break;
                case "4":
                    System.out.println("1");
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
