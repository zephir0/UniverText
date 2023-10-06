package com.univertext.analyzer;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CsvFileAnalyzerTest {

    private CsvFileAnalyzer csvFileAnalyzer;
    private FileContent mockFileContent;

    @BeforeEach
    public void setUp() {
        mockFileContent = mock(FileContent.class);
        csvFileAnalyzer = new CsvFileAnalyzer();
    }

    @Test
    public void calculateSumTest() {
        when(mockFileContent.getLines()).thenReturn(List.of("1.5,2.5", "3.5,4.5", "notANumber,5.5"));

        double result = csvFileAnalyzer.calculateSum(mockFileContent, 1);

        assertEquals(12.5, result);
    }

    @Test
    public void countRepeatingWordsTest() {
        when(mockFileContent.getLines()).thenReturn(List.of("apple,orange", "APPLE,banana", "apple,grape"));

        Map<String, Long> result = csvFileAnalyzer.countRepeatingWords(mockFileContent, 0);
        Map<String, Long> expectedResult = Map.of("apple", 3L);

        assertEquals(result, expectedResult);
    }
}
