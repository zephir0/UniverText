package com.univertext.converter;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonToYamlConverterTest {

    @Test
    public void conversionTest() {
        JsonToYamlConverter converter = new JsonToYamlConverter();
        FileContent input = new FileContent("path.json", List.of("{\"name\":\"John\"}"));
        FileContent result = converter.convert(input);
        assertEquals("name: John\n", result.getLines().get(0));
    }
}
