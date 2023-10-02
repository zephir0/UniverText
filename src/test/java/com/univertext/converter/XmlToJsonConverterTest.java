package com.univertext.converter;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlToJsonConverterTest {

    @Test
    public void conversionTest() {
        XmlToJsonConverter converter = new XmlToJsonConverter();
        FileContent input = new FileContent("path.xml", List.of("<name>John</name>"));
        FileContent result = converter.convert(input);
        assertEquals("{\"name\":\"John\"}", result.getLines().get(0));
    }
}
