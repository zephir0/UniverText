package com.univertext.editor;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JsonFileEditorTest {

    private JsonFileEditor jsonFileEditor;

    @BeforeEach
    void setUp() {
        jsonFileEditor = new JsonFileEditor();
    }


    @Test
    void addJsonElementTest() {
        List<String> jsonLines = Arrays.asList(
                "[",
                "   {",
                "       \"name\": \"John\",",
                "       \"age\": 30",
                "   }",
                "]"
        );
        FileContent fileContent = new FileContent("", jsonLines);

        String jsonElementToAdd = "{ \"name\": \"Alice\", \"age\": 25 }";

        jsonFileEditor.addJsonElement(fileContent, jsonElementToAdd);

        String expectedUpdatedJson = "[{ \"name\": \"John\", \"age\": 30 }, { \"name\": \"Alice\", \"age\": 25 }]";
        assertJsonEquals(expectedUpdatedJson, String.join("", fileContent.getLines()));
    }

    private void assertJsonEquals(String expected,
                                  String actual) {
        expected = expected.replaceAll("\\s+", "");
        actual = actual.replaceAll("\\s+", "");
        Assertions.assertEquals(expected, actual);
    }

}
