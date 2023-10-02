package com.univertext.io.reader;

import com.univertext.model.FileContent;

import java.util.Optional;

public interface FileReaderInterface {
    Optional<FileContent> readFile(String path);
}
