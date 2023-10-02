package com.univertext.editor;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class TextFileEditorTest {
    private TextFileEditor textFileEditor;

    @BeforeEach
    public void setUp() {
        textFileEditor = new TextFileEditor();
    }


    @Test
    public void convertToUpperCaseTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("test", "banana"));

        textFileEditor.convertToUpperCase(fileContent);

        List<String> expectedLines = Arrays.asList("TEST", "BANANA");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }
}
