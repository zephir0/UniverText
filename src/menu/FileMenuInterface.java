package menu;

import model.FileContent;
import model.FileType;

import java.util.Scanner;

public interface FileMenuInterface {
    void displayMenu(Scanner scanner,
                     FileType fileType, FileContent fileContent);
}
