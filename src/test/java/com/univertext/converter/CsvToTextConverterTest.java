package com.univertext.converter;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvToTextConverterTest {

    @Test
    public void conversionTest() {
        CsvToTextConverter converter = new CsvToTextConverter();
        FileContent input = new FileContent("path.csv", List.of("John,Doe,25"));
        FileContent result = converter.convert(input);
        assertEquals("John Doe 25", result.getLines().get(0));
    }
}
