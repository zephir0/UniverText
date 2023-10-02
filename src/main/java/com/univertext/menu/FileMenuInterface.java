package com.univertext.menu;

import com.univertext.model.FileContent;
import com.univertext.model.FileType;

import java.util.Scanner;

public interface FileMenuInterface {
    void displayMenu(Scanner scanner,
                     FileType fileType, FileContent fileContent);
}
