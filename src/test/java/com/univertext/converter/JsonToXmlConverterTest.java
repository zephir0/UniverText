package com.univertext.converter;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonToXmlConverterTest {

    @Test
    public void conversionTest() {
        JsonToXmlConverter converter = new JsonToXmlConverter();
        FileContent input = new FileContent("path.json", List.of("{\"name\":\"John\"}"));
        FileContent result = converter.convert(input);
        assertEquals("<name>John</name>", result.getLines().get(0));
    }
}
