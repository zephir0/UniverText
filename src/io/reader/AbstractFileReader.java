package io.reader;

import model.FileContent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public abstract class AbstractFileReader implements FileReaderInterface {
    @Override
    public Optional<FileContent> readFile(String path) {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            return Optional.empty();
        }
        try {
            return Optional.of(new FileContent(path, Files.readAllLines(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
