package com.univertext.writer;

import com.univertext.io.writer.FileOutputStreamProvider;
import com.univertext.io.writer.UniversalFileWriter;
import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniversalFileWriterTest {
    private UniversalFileWriter writer;
    private MockedStatic<ConsolePrinter> mockConsolePrinter;
    @Mock
    private FileOutputStreamProvider mockProvider;

    @BeforeEach
    public void setUp() {
        mockConsolePrinter = Mockito.mockStatic(ConsolePrinter.class);
        writer = new UniversalFileWriter(mockProvider);
    }

    @AfterEach
    public void tearDown() {
        mockConsolePrinter.close();
    }

    @Test
    public void testWriteFileSuccess() throws IOException {
        FileContent content = new FileContent("success/directory/example.json", List.of("Hello", "World"));
        when(mockProvider.get(anyString())).thenReturn(mock(OutputStream.class));

        writer.writeFile(content, FileType.TXT);

        verify(mockProvider, times(1)).get(anyString());
    }


    @Test
    public void testConsolePrinterIsInvokedOnSuccess() throws IOException {
        FileContent content = new FileContent("/success/directory/example.json", List.of("{\"key\":\"value\"}"));

        when(mockProvider.get(anyString())).thenReturn(mock(OutputStream.class));

        writer.writeFile(content, FileType.JSON);

        mockConsolePrinter.verify(() -> ConsolePrinter.printSuccess("File saved successfully."), times(1));
        mockConsolePrinter.verify(() -> ConsolePrinter.printSuccess(startsWith("Here is your new file -> ")), times(1));
    }

    @Test
    public void testConsolePrinterIsInvokedOnError() throws IOException {
        FileContent content = new FileContent("/nonexistent/directory/example.json", List.of("{\"key\":\"value\"}"));
        when(mockProvider.get(anyString())).thenThrow(new IOException("Test exception"));

        writer.writeFile(content, FileType.JSON);
        mockConsolePrinter.verify(() -> ConsolePrinter.printError("An error occurred while writing to the file: Test exception"), times(1));
    }
}
