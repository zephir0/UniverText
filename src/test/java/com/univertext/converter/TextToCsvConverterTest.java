package com.univertext.converter;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextToCsvConverterTest {

    @Test
    public void conversionTest() {
        TextToCsvConverter converter = new TextToCsvConverter();
        FileContent input = new FileContent("path.txt", List.of("John Doe 25"));
        FileContent result = converter.convert(input);
        assertEquals("John,Doe,25", result.getLines().get(0));
    }
}
