package com.univertext.editor;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class AbstractFileEditorTest {

    private AbstractFileEditor editor;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        editor = new AbstractFileEditor() {
        };
    }

    @Test
    void removeDuplicatesTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("line1", "line1", "line2"));

        editor.removeDuplicates(fileContent);

        List<String> expectedLines = Arrays.asList("line1", "line2");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }

    @Test
    void replaceWordsTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("hello world", "hello there"));

        editor.replaceWords(fileContent, "hello", "hi");

        List<String> expectedLines = Arrays.asList("hi world", "hi there");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }

    @Test
    void sortLinesTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("banana", "apple", "cherry"));

        editor.sortLines(fileContent);

        List<String> expectedLines = Arrays.asList("apple", "banana", "cherry");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }

    @Test
    void addLineNumberTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("line1", "line2"));

        editor.addLineNumber(fileContent);

        List<String> expectedLines = Arrays.asList("1. line1", "2. line2");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }

    @Test
    void filterLinesTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("apple", "banana", "cherry"));
        String[] keywords = {"apple", "banana"};
        editor.filterLines(fileContent, keywords);

        List<String> expectedLines = List.of("apple", "banana");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }

    @Test
    void reverseLinesTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("hello", "world"));

        editor.reverseLines(fileContent);

        List<String> expectedLines = Arrays.asList("olleh", "dlrow");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }
}
