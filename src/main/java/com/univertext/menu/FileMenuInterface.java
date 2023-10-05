package com.univertext.menu;

import com.univertext.model.FileContent;

import java.util.Scanner;

public interface FileMenuInterface {
    void displayMenu(Scanner scanner,
                     FileContent fileContent);
}
