package com.univertext.io.reader;

import java.util.List;

public class TextFileReader extends AbstractFileReader {
    @Override
    protected List<String> getExpectedFileTypes() {
        return List.of(".txt", ".doc", ".csv", ".html", ".rtf");
    }
}
