package com.univertext.reader;

import com.univertext.io.reader.TextFileReader;
import com.univertext.model.FileContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractFileReaderTest {

    @TempDir
    Path tempDir;

    private TextFileReader reader;

    @BeforeEach
    public void setUp() {
        reader = new TextFileReader();
    }

    @Test
    public void readFileValidFileTest() throws Exception {
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        Files.write(file, Arrays.asList("Line 1", "Line 2"));

        Optional<FileContent> content = reader.readFile(file.toString());

        assertTrue(content.isPresent());
        assertEquals(Arrays.asList("Line 1", "Line 2"), content.get().getLines());
    }

    @Test
    public void readFileInvalidFileTypeTest() throws Exception {
        Path file = Files.createFile(tempDir.resolve("test.json"));
        Files.write(file, Arrays.asList("Line 1", "Line 2"));

        Optional<FileContent> content = reader.readFile(file.toString());

        assertFalse(content.isPresent());
    }

    @Test
    public void readFileFileDoesNotExistTest() {
        Optional<FileContent> content = reader.readFile(tempDir.resolve("nonexistent.txt").toString());

        assertFalse(content.isPresent());
    }

    @Test
    public void readFilePathIsDirectoryTest() {
        Optional<FileContent> content = reader.readFile(tempDir.toString());

        assertFalse(content.isPresent());
    }
}