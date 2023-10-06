package com.univertext.io.reader;

import com.univertext.model.FileContent;
import com.univertext.printer.ConsolePrinter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public abstract class AbstractFileReader implements FileReaderInterface {
    protected abstract List<String> getExpectedFileTypes();

    @Override
    public Optional<FileContent> readFile(String path) {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            return Optional.empty();
        }

        List<String> expectedFileTypes = getExpectedFileTypes();
        boolean isFileTypeValid = expectedFileTypes.stream()
                .anyMatch(path::endsWith);

        if (!isFileTypeValid) {
            ConsolePrinter.printError("Invalid file type. Expected one of " + expectedFileTypes);
            return Optional.empty();
        }

        try {
            return Optional.of(new FileContent(path, Files.readAllLines(filePath)));
        } catch (IOException e) {
            ConsolePrinter.printError("An error occurred while reading the file: " + e.getMessage());
        }
        return Optional.empty();
    }
}
