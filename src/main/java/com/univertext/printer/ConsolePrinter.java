package com.univertext.printer;

public class ConsolePrinter {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[94m";
    public static final String ANSI_GREEN = "\u001B[92m";
    public static final String ANSI_PURPLE = "\u001B[95m";
    public static final String ANSI_WHITE = "\u001B[97m";

    public static final String ANSI_BOLD = "\u001B[1m";

    public static final String ANSI_CYAN = "\u001B[96m";


    public static void print(String message) {
        System.out.println(ANSI_WHITE + message + ANSI_RESET);
    }

    public static void printSuccess(String response) {
        System.out.println(ANSI_BOLD + ANSI_CYAN + response + ANSI_RESET);
    }

    public static void printError(String errorMessage) {
        System.err.println(ANSI_BOLD + ANSI_RED + errorMessage + ANSI_RESET);
    }

    public static void printMenuTitle(String title) {
        System.out.println(ANSI_BOLD + ANSI_PURPLE + title + ANSI_RESET);
    }

    public static void printLogo(String logo) {
        System.out.println(ANSI_BOLD + ANSI_GREEN + logo + ANSI_RESET);
    }

    public static void printMenu(String menu) {
        System.out.println(ANSI_BOLD + ANSI_BLUE + menu + ANSI_RESET);
    }
}
