package io.reader;

import model.FileContent;

import java.util.Optional;

public interface FileReaderInterface {
    Optional<FileContent> readFile(String path);
}
