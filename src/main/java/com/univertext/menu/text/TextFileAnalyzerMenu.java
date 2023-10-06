package com.univertext.menu.text;

import com.univertext.analyzer.TextFileAnalyzer;
import com.univertext.menu.FileMenuInterface;
import com.univertext.model.FileContent;
import com.univertext.printer.ConsolePrinter;

import java.util.Map;
import java.util.Scanner;

public class TextFileAnalyzerMenu implements FileMenuInterface {
    private final TextFileAnalyzer textFileAnalyzer;

    public TextFileAnalyzerMenu(TextFileAnalyzer textFileAnalyzer) {
        this.textFileAnalyzer = textFileAnalyzer;
    }

    @Override
    public void launchMenu(Scanner scanner,
                           FileContent fileContent) {
        do {
            ConsolePrinter.printMenuTitle("\nText Analyze Menu: ");
            ConsolePrinter.printMenu("""
                    1. Count lines.
                    2. Count the number of words.
                    3. Count characters.
                    4. Provide information about the most frequent words, average word length, etc.
                    5. Search for a specific phrase or pattern in the file and return the number of occurrences or their locations.
                    6. Provide statistics on the frequency of individual characters.
                    7. Analyze the text for reading difficulty.
                    8. Determine the language of the text.
                    9. Return lines that repeat in the text.
                    10. Create a histogram showing the frequency of various words.
                    11. Analyze the sentiment of the text.
                    12. Detect inconsistencies in the text.
                    13. Generate a summary of the text.
                    14. Identify the main topics of the text.
                    15. Return encoding information about the file.
                    16. Return to the main menu.""");
            switch (scanner.nextLine()) {
                case "1" -> {
                    int countedLines = textFileAnalyzer.countLines(fileContent);
                    ConsolePrinter.printSuccess("Number of lines: " + countedLines);
                }
                case "2" -> {
                    long countedWords = textFileAnalyzer.countWords(fileContent);
                    ConsolePrinter.printSuccess("Number of words: " + countedWords);
                }
                case "3" -> {
                    int countedCharacters = textFileAnalyzer.countCharacters(fileContent);
                    ConsolePrinter.printSuccess("Number of characters: " + countedCharacters);
                }
                case "4" -> {
                    Map<String, Long> wordStatistics = textFileAnalyzer.wordStatistics(fileContent);
                    wordStatistics.forEach((word, count) -> ConsolePrinter.printSuccess(word + ": " + count));

                }
                case "16" -> {
                    return;
                }
                default -> ConsolePrinter.printError("Invalid option. Please try again.");
            }
        } while (true);
    }
}
