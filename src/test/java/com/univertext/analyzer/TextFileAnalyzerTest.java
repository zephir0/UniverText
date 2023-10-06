package com.univertext.analyzer;

import com.univertext.model.FileContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextFileAnalyzerTest {
    private final TextFileAnalyzer analyzer = new TextFileAnalyzer();
    private FileContent mockFileContent;

    @BeforeEach
    void setUp() {
        mockFileContent = mock(FileContent.class);
    }

    @Test
    public void testCountLines() {
        mockFileContent = mock(FileContent.class);
        when(mockFileContent.getLines()).thenReturn(Arrays.asList("Hello world", "Test test", "It's a sunny day"));

        int expectedLinesNumber = analyzer.countLines(mockFileContent);

        assertEquals(3, expectedLinesNumber);
    }

    @Test
    public void testCountWords() {
        mockFileContent = mock(FileContent.class);
        when(mockFileContent.getLines()).thenReturn(Arrays.asList("Hello world", "Test test", "It's a sunny day"));

        long expectedWordsNumber = analyzer.countWords(mockFileContent);

        assertEquals(8, expectedWordsNumber);
    }

    @Test
    public void testCountCharacters() {
        mockFileContent = mock(FileContent.class);
        when(mockFileContent.getLines()).thenReturn(Arrays.asList("Hello world", "Test test", "It's a sunny day"));

        int expectedCharactersNumber = analyzer.countCharacters(mockFileContent);

        assertEquals(36, expectedCharactersNumber);
    }

    @Test
    public void testWordStatistics() {
        mockFileContent = mock(FileContent.class);
        when(mockFileContent.getLines()).thenReturn(Arrays.asList("Hello world", "Test test"));

        Map<String, Long> expectedResult = Map.of("hello", 1L, "world", 1L, "test", 2L);
        Map<String, Long> stringLongMap = analyzer.wordStatistics(mockFileContent);

        assertEquals(expectedResult, stringLongMap);

    }

}
