package com.univertext.io.writer;

import com.univertext.model.FileContent;
import com.univertext.model.FileType;
import com.univertext.printer.ConsolePrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class UniversalFileWriter {
    private final FileOutputStreamProvider fileOutputStreamProvider;

    public UniversalFileWriter() {
        this.fileOutputStreamProvider = new DefaultFileOutputStreamProvider();
    }

    public UniversalFileWriter(FileOutputStreamProvider provider) {
        this.fileOutputStreamProvider = provider;
    }

    public void writeFile(FileContent fileContent, FileType fileType) {
        String outputPath = getOutputPath(fileContent.getPath(), fileType);

        int fileNumber = 1;
        while (fileExists(outputPath)) {
            outputPath = appendNumberToFileName(outputPath, fileNumber);
            fileNumber++;
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStreamProvider.get(outputPath)))) {
            for (String line : fileContent.getLines()) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            ConsolePrinter.printSuccess("File saved successfully.");
            ConsolePrinter.printSuccess("Here is your new file -> " + outputPath);
        } catch (IOException e) {
            ConsolePrinter.printError("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private String getOutputPath(String originalPath,
                                 FileType fileType) {
        int lastDot = originalPath.lastIndexOf(".");
        String pathWithoutExtension = (lastDot == -1) ? originalPath : originalPath.substring(0, lastDot);
        int lastSeparator = pathWithoutExtension.lastIndexOf(File.separator);
        String directory = pathWithoutExtension.substring(0, lastSeparator + 1);
        return directory + "output" + getFileExtension(fileType);
    }

    private String appendNumberToFileName(String originalPath,
                                          int number) {
        int lastDot = originalPath.lastIndexOf(".");
        String baseName = originalPath.substring(0, lastDot);
        String baseWithoutNumber = baseName.replaceAll("\\d+$", "");
        return baseWithoutNumber + number + originalPath.substring(lastDot);
    }


    private String getFileExtension(FileType fileType) {
        return switch (fileType) {
            case CSV -> ".csv";
            case JSON -> ".json";
            case TXT -> ".txt";
            case XML -> ".xml";
            case YAML -> ".yaml";
        };
    }

    private boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
