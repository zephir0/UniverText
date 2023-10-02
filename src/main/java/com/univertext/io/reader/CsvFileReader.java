package com.univertext.io.reader;

import java.util.List;

public class CsvFileReader extends AbstractFileReader {
    @Override
    protected List<String> getExpectedFileTypes() {
        return List.of(".csv,", ".xls", ".xlsx");
    }
}