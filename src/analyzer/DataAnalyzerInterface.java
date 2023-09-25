package analyzer;

import model.FileContent;

public interface DataAnalyzerInterface {
    void calculateSum(FileContent data, int columnIndex);
    void countRepeatingWords(FileContent data, int columnIndex);
}
