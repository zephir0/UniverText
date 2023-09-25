package io.writer;

import model.FileContent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvFileWriter implements FileWriterInterface {
    @Override
    public void writeFile(FileContent fileContent) {
        String filePath = fileContent.getPath();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : fileContent.getLines()) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            System.out.println("File successfully saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
