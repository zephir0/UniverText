package com.univertext.analyzer;

import com.univertext.model.FileContent;

public interface DataAnalyzerInterface {
    void calculateSum(FileContent data, int columnIndex);
    void countRepeatingWords(FileContent data, int columnIndex);
}
