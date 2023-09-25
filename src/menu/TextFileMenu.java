package menu;

import model.FileContent;
import model.FileType;
import printer.ConsolePrinter;

import java.util.Scanner;

public class TextFileMenu implements FileMenuInterface {
    @Override
    public void displayMenu(Scanner scanner,
                            FileType fileType,
                            FileContent fileContent) {
        while (true) {
            ConsolePrinter.print("""
                    \n1. Display file content.
                    2. Edit file.
                    3. Analyze file.
                    4. Convert to CSV.
                    5. Return to main menu.""");
            switch (scanner.nextLine()) {
                case "1" -> fileContent.getLines().forEach(ConsolePrinter::print);
                case "2" -> editFile(scanner);
                case "5" -> {
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        }
    }

    private void editFile(Scanner scanner) {
        ConsolePrinter.print("""
                \n1. Sort alphabetically A-Z.
                2. Sort alphabetically Z-A.
                3. Replace word.
                4. Remove duplicates.
                5. Go back.""");
        while (true) {
            ConsolePrinter.print("Select an option: ");
            switch (scanner.nextLine()) {
                case "1" -> System.out.println();
                case "5" -> {
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        }
    }
}
