package com.univertext.io.reader;

import java.util.List;

public class JsonFileReader extends AbstractFileReader {
    @Override
    protected List<String> getExpectedFileTypes() {
        return List.of(".json");
    }
}
