package com.univertext.editor;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class CsvFileEditorTest {

    private CsvFileEditor editor;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        editor = new CsvFileEditor();
    }

    @Test
    void changeDelimiterTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("col1,col2", "data1,data2"));

        editor.changeDelimiter(fileContent, ";");

        List<String> expectedLines = Arrays.asList("col1;col2", "data1;data2");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }

    @Test
    void transformToTableTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("col1,col2", "data1,data2"));
        // TODO: 9/27/2023
        editor.transformToTable(fileContent);
    }

    @Test
    void addColumnTest() {
        FileContent fileContent = new FileContent("", Arrays.asList("data1", "data2"));

        editor.addColumn(fileContent, "col3");

        List<String> expectedLines = Arrays.asList("data1,col3", "data2,col3");
        assertIterableEquals(expectedLines, fileContent.getLines());
    }
}
